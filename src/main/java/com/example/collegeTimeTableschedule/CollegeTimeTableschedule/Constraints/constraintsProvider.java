package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Constraints;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Course;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Room;
import org.optaplanner.core.api.score.buildin.hardmediumsoftbigdecimal.HardMediumSoftBigDecimalScore;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import java.time.Duration;
import java.time.LocalTime;

public class constraintsProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
          //hardConstraints (With these conflicts , It is very tough to schedule courses because there is only one room for one course at the time)
               roomConflict(constraintFactory) ,
               teacherConflict(constraintFactory),
               studentGroupConflict(constraintFactory),
               noOverLappingCourses(constraintFactory),


           //softConstraints (with this we can manage but it is good to not break it also )
            teacherRoomStability(constraintFactory),
            teacherTimeEfficiency(constraintFactory),
            studentGroupSubjectVariety(constraintFactory),
            studentRoomStability(constraintFactory),
            studentGroupTimeEfficiency(constraintFactory),
                OneRoomOneStudentGroup(constraintFactory)


        };
    }


    private boolean roomStability(Course course1 , Course course2){
        Room room1 = course1.getRoom() ;
        Room room2 = course2.getRoom() ;

        return room1 != room2;
    }

    private boolean getTimings(Course course1, Course course2) {

        LocalTime endTimeCourse1 = course1.getTimeSlot().getEndTime();
        LocalTime startTimeCourse2 = course2.getTimeSlot().getStartTime();
        long betweenMinutes = Duration.between(endTimeCourse1, startTimeCourse2).toMinutes();

        return betweenMinutes >= 0 ;
    }


    private Constraint roomConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                //select the two different courses
                .fromUniquePair(Course.class,
                        //in the same timeSlot
                        Joiners.equal(Course::getTimeSlot),
                        //in the same room
                            Joiners.equal(Course::getRoom))
                .penalize("Room conflict", HardSoftScore.ONE_HARD);
    }


    private Constraint studentRoomStability(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Course.class)
                .join(Course.class , Joiners.equal(Course::getStudentGroup))
                .filter((course1, course2) -> roomStability(course1,course2))
                .penalize("Room Not Stable" , HardSoftScore.ONE_SOFT);
    }

    private Constraint OneRoomOneStudentGroup(ConstraintFactory constraintFactory) {
        return constraintFactory
                .from(Course.class)
                .join(Course.class ,Joiners.equal(Course::getStudentGroup))
                .filter((course1,course2) -> checkForRoom(course1,course2))
                .penalize("Room Of Student Group is not stable" , HardSoftScore.ONE_SOFT);

    }

    private boolean checkForRoom(Course course1, Course course2) {
        if(course1.getRoom() ==course2.getRoom()){
            if (course1.getStudentGroup()==course2.getStudentGroup()){
                if(course1.getTimeSlot()!=course2.getTimeSlot()){
                    return false;
                }
            }
        }

        // penality given
             return true ;

    }

    private Constraint noOverLappingCourses(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(Course.class ,
                        Joiners.equal(Course::getTeacher))
                .filter((course1, course2) -> getTimings(course1 , course2))
                .penalize("Overlapped Courses Not Possible For One Teacher", HardSoftScore.ONE_HARD) ;
    }


    private Constraint studentGroupConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .fromUniquePair(Course.class,
                Joiners.equal(Course::getTimeSlot),
                Joiners.equal(Course::getStudentGroup))
                .penalize("studentGroup conflict" , HardSoftScore.ONE_HARD);
    }


    private Constraint teacherConflict(ConstraintFactory constraintFactory) {
       return constraintFactory
                .fromUniquePair(Course.class,
                Joiners.equal(Course::getTimeSlot),
                Joiners.equal(Course::getTeacher))
                .penalize("Teacher Conflict" , HardSoftScore.ONE_HARD) ;
    }


    private Constraint studentGroupSubjectVariety(ConstraintFactory constraintFactory) {
        /* student dislikes continuity of  same course without any gap*/
        return  constraintFactory
                .from(Course.class)
                .join(Course.class,
                     Joiners.equal(Course::getSubject),
                        Joiners.equal(Course::getStudentGroup),
                 Joiners.equal((course) -> course.getTimeSlot().getDayOfWeek()))
                  .filter((course1 , course2) -> {
                Duration between = Duration.between(course1.getTimeSlot().getEndTime(),
                        (course2.getTimeSlot().getStartTime()) );
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(20)) <= 0;
                })
                .penalize("studentGroup Subject Conflict",HardSoftScore.ONE_SOFT) ;
    }

   private Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {

        return constraintFactory
                .from(Course.class)
                .join(Course.class,
                        Joiners.equal(Course::getTeacher),
                          Joiners.equal((course) -> course.getTimeSlot()))
                .filter((course1 , course2) -> getTimings(course1,course2))
                .penalize("Teacher Time Efficiency"  , HardSoftScore.ONE_SOFT) ;

   }

    private Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
       return constraintFactory
               .from(Course.class)
               .join(Course.class,
                       Joiners.equal(Course::getTeacher))
               .filter((course1,course2)-> roomStability(course1,course2))
               .penalize("Teacher Room Conflict", HardSoftScore.ONE_SOFT) ;
    }

    private Constraint studentGroupTimeEfficiency(ConstraintFactory constraintFactory) {

        return constraintFactory
                .from(Course.class)
                .join(Course.class,
                        Joiners.equal(Course::getStudentGroup),
                        //ensures that the courses being considered belong to the same student group and are scheduled on the same day of the week.
                        Joiners.equal((course) -> course.getTimeSlot()))
                .filter((course1 , course2) -> getTimings(course1,course2))
                .penalize("StudentGroup Time Efficiency"  , HardSoftScore.ONE_SOFT) ;

    }


}
