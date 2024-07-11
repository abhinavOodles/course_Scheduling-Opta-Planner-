package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;


import java.util.List;

@PlanningSolution
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class TimeTable {

    @ValueRangeProvider
    @ProblemFactCollectionProperty
   // @JsonDeserialize(using = DateTimeDeserializer.class)
    private List<TimeSlot> timeslotList;


    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<Room> roomList;

    @PlanningEntityCollectionProperty
    private List<Course> courseList;



    @PlanningScore
    private HardSoftScore score;

    private SolverStatus solverStatus;

    public TimeTable() {
    }

    public TimeTable(List<Course> courseList, List<Room> roomList, HardSoftScore score, SolverStatus solverStatus, List<TimeSlot> timeslotList) {
        this.courseList = courseList;
        this.roomList = roomList;
        this.score = score;
        this.solverStatus = solverStatus;
      this.timeslotList = timeslotList;
    }

    public TimeTable(List<Course> courseList, List<Room> roomList, List<TimeSlot> timeslotList) {
        this.courseList = courseList;
        this.roomList = roomList;
        this.timeslotList = timeslotList;
    }


}
