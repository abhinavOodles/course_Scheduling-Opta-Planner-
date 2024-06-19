package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS.CourseDTO;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Course;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo ;

    public String save(CourseDTO courseDTO) {

        Course  course  = new Course() ;

        course.setStudentGroup(courseDTO.getStudentGroup());
        course.setSubject(courseDTO.getSubject());
        course.setTeacher(courseDTO.getTeacher());

        courseRepo.save(course);
        return "Course is saved Successfully Saved" ;


    }

//    public void updateRoom() {
//        List<Course> courseList = courseRepo.findAll() ;
//
//        for (Course course : courseList) {
//
//            if (course.getRoom() != null || course.getTimeSlot() != null) {
//                course.setRoom(null);
//                course.setTimeSlot(null);
//                courseRepo.save(course);
//            }
//        }
    }







