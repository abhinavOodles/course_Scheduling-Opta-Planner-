package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {

    @PlanningId
    @Id
    @GeneratedValue
    private int id ;

    private String roomName ;

    @Override
    public String toString() {
        return roomName;
    }

    public Room() {
    }

    public Room(String s) {
    }

    public Room(int id, String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
