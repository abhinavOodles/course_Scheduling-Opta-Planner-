package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeTable;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;


public class SolverListener implements SolverEventListener<TimeTable> {

    @Override
    public void bestSolutionChanged(BestSolutionChangedEvent<TimeTable> solver) {
        TimeTable bestSolution = solver.getNewBestSolution();
        System.out.println("New best solution found with score: " + bestSolution.getScore());
    }



    public void startedSolving (){
        System.out.println("Start Solving :: for :: problemId");
    }

    public void stopSolved(){
        System.out.println("Solving stop here :: with finalScore :: ");
    }


}