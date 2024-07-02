package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Listener;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Course;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Room;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeTable;
import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;

import java.time.Duration;
import java.util.List;

public class CourseListener implements VariableListener<TimeTable, Room> {

    @Override
    public void beforeVariableChanged(ScoreDirector<TimeTable> scoreDirector, Room room) {

    }

    @Override
    public void afterVariableChanged(ScoreDirector<TimeTable> scoreDirector, Room room) {

    }

    @Override
    public void beforeEntityAdded(ScoreDirector<TimeTable> scoreDirector, Room room) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<TimeTable> scoreDirector, Room room) {

    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<TimeTable> scoreDirector, Room room) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<TimeTable> scoreDirector, Room room) {

    }

    public void updateWorkingHoursOfRoom(ScoreDirector scoreDirector , Room room){
        if (room.getCourses() == null){
            return;
        }
        else{
            List<Course> courses = room.getCourses() ;
            for (Course course : courses){
                Duration duration = Duration.between(course.getTimeSlot().getEndTime(), course.getTimeSlot().getStartTime());
                long minutes = duration.toMinutes() ;
                room.setWorkingHoursForARoomToAParticularCourse(minutes);
            }
        }
    }
}
