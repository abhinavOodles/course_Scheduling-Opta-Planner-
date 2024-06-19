package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@PlanningEntity
@Entity
public class Course {

    @PlanningId
    @Id @GeneratedValue
    private Long id  ;

    private String subject;
    private String teacher;
    private String studentGroup;

    @PlanningVariable
    @ManyToOne
    private TimeSlot timeSlot ;

    @PlanningVariable
    @ManyToOne
    private Room room ;

    @Override
    public String toString() {
        return subject + "(" + id + ")";
    }
    public Course() {
    }

    public Course(Long id, Room room, String studentGroup, String subject, String teacher, TimeSlot timeSlot) {
        this.id = id;
        this.room = room;
        this.studentGroup = studentGroup;
        this.subject = subject;
        this.teacher = teacher;
        this.timeSlot = timeSlot;
    }

    public Course(String subject, String teacher, String studentGroup) {
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }


}
