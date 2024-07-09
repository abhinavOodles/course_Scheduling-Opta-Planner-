package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSlot {
    @PlanningId
    @Id
    @GeneratedValue
    private Long id;


    private DayOfWeek dayOfWeek;


    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @JsonDeserialize(using = DateTimeDeserializer.class)
   @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @Override
    public String toString() {
        return dayOfWeek + " " + startTime;
    }

    public TimeSlot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlot(long id, DayOfWeek dayOfWeek, LocalTime startTime) {
        this(dayOfWeek, startTime, startTime.plusMinutes(60));
        this.id = id;
    }

    public TimeSlot() {

    }


}
