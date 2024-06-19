package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain;

import lombok.Getter;
import lombok.Setter;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Room {

    @PlanningId
    @Id
    @GeneratedValue
    private int id;

    private String roomName;

    @Transient
    @InverseRelationShadowVariable(sourceVariableName = "room")
    private Set<Course> courses = new HashSet<>();

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

}
