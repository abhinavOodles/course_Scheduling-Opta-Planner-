package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;

import java.util.List;

@PlanningSolution
public class TimeTable {

    @ValueRangeProvider
    @ProblemFactCollectionProperty
    private List<TimeSlot> timeslotList;

   // @ValueRangeProvider(id = "roomRange")
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

    public  List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

    public List<TimeSlot> getTimeslotList() {
        return timeslotList;
    }

    public void setTimeslotList(List<TimeSlot> timeslotList) {
        this.timeslotList = timeslotList;
    }


}
