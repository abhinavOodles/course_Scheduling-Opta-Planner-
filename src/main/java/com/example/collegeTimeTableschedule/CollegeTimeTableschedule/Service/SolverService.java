package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Course;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Room;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeSlot;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeTable;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.CourseRepo;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.RoomRepo;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.TimeSlotRepo;
import lombok.extern.slf4j.Slf4j;
import org.optaplanner.core.api.score.ScoreExplanation;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.constraint.ConstraintMatch;
import org.optaplanner.core.api.score.constraint.Indictment;
import org.optaplanner.core.api.solver.*;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.SolverManagerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@Transactional
public class SolverService {


    @Autowired
    private SolverManager<TimeTable , Long> solverManager ;
    @Autowired
    private SolutionManager <TimeTable , HardSoftScore> solutionManager;


    public static final Long Time_Table_Id = 1L;

    @Autowired
    private TimeSlotRepo timeslotRepository;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private CourseRepo courseRepo;


    SolverService() {
     SolverConfig solver = SolverConfig.createFromXmlResource("Solver.xml");
            this.solverManager  = SolverManager.create(solver, new SolverManagerConfig());
        SolverFactory<TimeTable> solverFactory = SolverFactory.create(solver);
        this.solutionManager = SolutionManager.create(solverFactory);
    }

    public List<Course> solverConfig(){
        TimeTable timeTable = new TimeTable() ;

        List<Course> courseList = courseRepo.findAll();
        List<Room> rooms = roomRepo.findAll();
        List<TimeSlot> timeSlots = timeslotRepository.findAll();

        timeTable.setCourseList(courseList);
        timeTable.setRoomList(rooms);
        timeTable.setTimeslotList(timeSlots);

        log.info("Solving started:: ");
        SolverJob<TimeTable, Long> solverJob = solverManager.solve(1l, timeTable);
        TimeTable solution;
        try {
            // Returns only after solving terminates
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        ScoreExplanation<TimeTable, HardSoftScore> scoreExplanation = solutionManager.explain(solution);
        Map<Object, Indictment<HardSoftScore>> indictmentMap = scoreExplanation.getIndictmentMap();

        for (Course process : solution.getCourseList()) {
            Indictment<HardSoftScore> indictment = indictmentMap.get(process);
            if (indictment == null) {
                continue;
            }
            // The score impact of that planning entity
            HardSoftScore totalScore = indictment.getScore();

            for (ConstraintMatch<HardSoftScore> constraintMatch : indictment.getConstraintMatchSet()) {
                String constraintName = constraintMatch.getConstraintName();
                HardSoftScore score = constraintMatch.getScore();
                log.info("CourseId::::::::::{},Constraint name ::::::{},:::::::socre{}",process.getId(), constraintName,score);
            }
        }

      return solution.getCourseList() ;
    }

//    public TimeTable findById(Long id) {
//
//        TimeTable timeTable = new TimeTable();
//
//        if (!(Time_Table_Id.equals(id))) {
//            throw new IllegalArgumentException("Wrong Id ->" + id);
//    } else {
//            return  new TimeTable(
//                    courseRepo.findAll(),
//                    roomRepo.findAll(),
//                    timeslotRepository.findAll()
//            );

//            List<Course> courseList = courseRepo.findAll();
//            List<Room> rooms = roomRepo.findAll();
//            List<TimeSlot> timeSlots = timeslotRepository.findAll();
//
//            timeTable.setCourseList(courseList);
//            timeTable.setRoomList(rooms);
//            timeTable.setTimeslotList(timeSlots);
//
//            Long jobId = 1l;
//
//
//            SolverJob<TimeTable, Long> solverJob = solverManager.solve(jobId, timeTable);
//            TimeTable solution;
//            try {
//                // Returns only after solving terminates
//                solution = solverJob.getFinalBestSolution();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//
//
//            ScoreExplanation<TimeTable, HardSoftScore> scoreExplanation = solutionManager.explain(solution);
//            return timeTable ;
      //  }

        //}

//        public void save(TimeTable timeTable) {
//            for (Course course : timeTable.getCourseList()) {
//                courseRepo.save(course);
//            }
        //}
////    SolverService() {
//    private SolverConfig solver = SolverConfig.createFromXmlResource("Solver.xml");
////        this.solverManager  = SolverManager.create(solver,new SolverManagerConfig());
////        SolverFactory<TimeTable> solverFactory = SolverFactory.create(solver);
////        this.solutionManager = SolutionManager.create(solverFactory);
////    }
//    public TimeTable solveTimeTable(TimeTable timeTable) {
//        SolverFactory<TimeTable> solverFactory = SolverFactory.create(solver);
//        Solver<TimeTable> solver = solverFactory.buildSolver();
//        SolverJob<TimeTable, Long> solverJob = solverManager.solve(Time_Table_Id, timeTable);
//        try {
//            return solverJob.getFinalBestSolution();
//        } catch (InterruptedException e) {
//            throw new RuntimeException("Solver job interrupted", e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//    }



    }

