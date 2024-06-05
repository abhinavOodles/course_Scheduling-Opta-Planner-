package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TimeSlotRepo extends JpaRepository<TimeSlot ,Long> {
}
