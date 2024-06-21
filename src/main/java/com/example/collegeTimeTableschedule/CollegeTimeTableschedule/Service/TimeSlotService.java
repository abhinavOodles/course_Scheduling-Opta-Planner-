package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS.TimeSlotDTO;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeSlot;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.TimeSlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TimeSlotService {

    @Autowired
    TimeSlotRepo timeSlotRepo ;

    public String save(TimeSlotDTO timeSlotDTO) {

        TimeSlot timeSlot = new TimeSlot() ;

        timeSlot.setStartTime(timeSlotDTO.getStartTime());
        timeSlot.setEndTime(timeSlotDTO.getEndTime());
        timeSlot.setDayOfWeek(timeSlotDTO.getDayOfWeek());

        timeSlotRepo.save(timeSlot) ;

        return "TimeSlot Updated successfully" ;
    }
}
