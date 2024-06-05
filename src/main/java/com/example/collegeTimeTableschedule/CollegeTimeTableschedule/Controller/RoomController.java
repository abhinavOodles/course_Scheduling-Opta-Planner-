package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Controller;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS.RoomDTO;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService  ;

    @PostMapping("/save")
    public ResponseEntity<String> saveRoom (@RequestBody RoomDTO roomDTO){
        return ResponseEntity.ok(roomService.save(roomDTO)) ;
    }
}
