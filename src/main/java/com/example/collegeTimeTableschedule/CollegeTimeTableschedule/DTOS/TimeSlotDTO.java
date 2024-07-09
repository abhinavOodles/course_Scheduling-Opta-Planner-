package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalTime;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSlotDTO {

    @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalTime startTime ;

    @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalTime endTime ;

    private DayOfWeek dayOfWeek ;
}
