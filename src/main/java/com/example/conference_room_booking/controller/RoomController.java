package com.example.conference_room_booking.controller;

import com.example.conference_room_booking.dto.request.BookingRequest;
import com.example.conference_room_booking.entity.Room;
import com.example.conference_room_booking.services.BookingService;
import com.example.conference_room_booking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
   private RoomService roomService;



    @GetMapping("/available")
    public List<Room> getAvailableRooms(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startTime,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endTime) {

        return roomService.getAvailableRooms(startTime, endTime);
    }





}
