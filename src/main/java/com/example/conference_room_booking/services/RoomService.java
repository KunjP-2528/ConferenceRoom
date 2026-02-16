package com.example.conference_room_booking.services;

import com.example.conference_room_booking.dto.request.BookingRequest;
import com.example.conference_room_booking.entity.Booking;
import com.example.conference_room_booking.entity.Employee;
import com.example.conference_room_booking.entity.Room;
import com.example.conference_room_booking.repository.BookingRepository;
import com.example.conference_room_booking.repository.EmployeeRepository;
import com.example.conference_room_booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;



    //getAvailable Room
    public List<Room> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {
        return roomRepository.findAvailableRooms(startTime, endTime);
    }




}
