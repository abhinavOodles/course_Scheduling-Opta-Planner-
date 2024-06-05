package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class TimeSlot {
    @PlanningId
    @Id
    @GeneratedValue
    private Long id;


    private DayOfWeek dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;

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

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
