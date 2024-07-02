package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Listener.CourseListener;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.ShadowVariable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @ShadowVariable(variableListenerClass = CourseListener.class, sourceVariableName = "room")
    private Long  workingHoursForARoomToAParticularCourse;

    @Transient
    @InverseRelationShadowVariable(sourceVariableName = "room")
    private List<Course> courses = new ArrayList<>();

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
