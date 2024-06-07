package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Course;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeTable;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.CourseRepo;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.RoomRepo;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.TimeSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class SolverService {

    public static final Long Time_Table_Id = 1L;

    @Autowired
    private TimeSlotRepo timeslotRepository;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private CourseRepo courseRepo;

    public TimeTable findById(Long id) {
        if (!(Time_Table_Id.equals(id))) {
            throw new IllegalArgumentException("Wrong Id ->" + id);
        } else {
            return new TimeTable(
                    courseRepo.findAll(),
                    roomRepo.findAll(),
                    timeslotRepository.findAll()
            );
        }
}
        public void save(TimeTable timeTable) {
            for (Course course : timeTable.getCourseList()) {
                courseRepo.save(course);
            }
        }




    }
