package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course , Long> {
}
