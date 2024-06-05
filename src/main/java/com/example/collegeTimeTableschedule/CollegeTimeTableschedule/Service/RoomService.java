package com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Service;

import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.DTOS.RoomDTO;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Domain.Room;
import com.example.collegeTimeTableschedule.CollegeTimeTableschedule.Repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo ;
    public String save(RoomDTO roomDTO) {
        Room room = new Room() ;
        room.setRoomName(roomDTO.getName());

        roomRepo.save(room);

        return "RoomName-Number Saved Successfully";
    }
}
