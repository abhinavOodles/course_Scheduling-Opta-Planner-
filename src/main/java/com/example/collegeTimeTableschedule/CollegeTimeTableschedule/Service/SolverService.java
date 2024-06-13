package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Course;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Room;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeSlot;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.TimeTable;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.CourseRepo;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.RoomRepo;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.TimeSlotRepo;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolutionManager;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.SolverManagerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class SolverService {


    @Autowired
    private SolverManager<TimeTable , Long> solverManager ;
    @Autowired
    private SolutionManager <TimeTable , HardSoftScore> solutionManager;

    SolverService() {
        SolverConfig solver = SolverConfig.createFromXmlResource("Solver.xml");
        this.solverManager  = SolverManager.create(solver);
        SolverFactory<TimeTable> solverFactory = SolverFactory.create(solver);
        this.solutionManager = SolutionManager.create(solverFactory);
    }

    public static final Long Time_Table_Id = 1L;

    @Autowired
    private TimeSlotRepo timeslotRepository;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private CourseRepo courseRepo;


    public TimeTable findById(Long id) {

        TimeTable timeTable = new TimeTable();

        if (!(Time_Table_Id.equals(id))) {
            throw new IllegalArgumentException("Wrong Id ->" + id);
     } else {
//            return  new TimeTable(
//                    courseRepo.findAll(),
//                    roomRepo.findAll(),
//                    timeslotRepository.findAll()
//            );
            List<Course> courseList = courseRepo.findAll();
            List<Room> rooms = roomRepo.findAll() ;
            List<TimeSlot> timeSlots = timeslotRepository.findAll() ;

            timeTable.setCourseList(courseList);
            timeTable.setRoomList(rooms);
            timeTable.setTimeslotList(timeSlots);
        }



        SolverJob<TimeTable, Long> solverJob = solverManager.solve(Time_Table_Id,timeTable);
        TimeTable solution;

        try {
            solution = solverJob.getFinalBestSolution();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return solution ;
}
        public void save(TimeTable timeTable) {
            for (Course course : timeTable.getCourseList()) {
                courseRepo.save(course);
            }
        }



    }
