package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.DayOfWeek;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeSlotDTO {
    private LocalTime startTime ;
    private LocalTime endTime ;
    private DayOfWeek dayOfWeek ;
}
