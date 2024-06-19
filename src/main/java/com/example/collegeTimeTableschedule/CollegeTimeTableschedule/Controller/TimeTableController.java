package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Controller;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeTable;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service.SolverService;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolutionManager;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/timetable")
public class TimeTableController {

    @Autowired
    private SolverService service ;

    @Autowired
    private SolverManager<TimeTable , Long> solverManager ;

    @Autowired
    private SolutionManager<TimeTable , HardSoftScore> solutionManager;


//    @GetMapping()
//    public TimeTable getTimeTable() {
//        SolverStatus solverStatus = getSolverStatus();
//        TimeTable solution = service.findById(SolverService.Time_Table_Id);
//        solutionManager.update(solution); // Sets the score
//        solution.setSolverStatus(solverStatus);
//        return solution;
//    }
//


//    @PostMapping("/solve")
//    public void solve(){
//        solverManager.solveAndListen(SolverService.Time_Table_Id,
//                service::findById,
//                service::save) ;
//    }

    @PostMapping("/configSolver")
    public ResponseEntity<?> solveByConfig(){
        return ResponseEntity.ok(service.solverConfig());
    }

    public SolverStatus getSolverStatus() {
        return solverManager.getSolverStatus(SolverService.Time_Table_Id);

    }
//    @PostMapping("/solve1")
//    public ResponseEntity<TimeTable> solveTimeTable() {
//        try {
//            TimeTable initialTimeTable = service.findById(SolverService.Time_Table_Id);
//            TimeTable solvedTimeTable = service.solveTimeTable(initialTimeTable);
//            return ResponseEntity.ok().body(solvedTimeTable);
//        } catch (IllegalStateException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping("/stopSolving")
    public void stopSolving() {
        solverManager.terminateEarly(SolverService.Time_Table_Id);
    }
}
