package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Controller;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS.CourseDTO;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Course;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.CourseRepo;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepo courseRepo;

    @PostMapping("/save")
    public ResponseEntity<String> saveCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.save(courseDTO));
    }

    @GetMapping("/inspect")
    public String inspectSolution() {

        List<Course> courseList = courseRepo.findAll();

        for (Course course1 : courseList) {
            System.out.println(course1.getId() +" "+ course1.getRoom() +" "+course1.getTimeSlot().getStartTime());
        }
        return "Check the console of IJ" ;
    }
}
