package com.example.conference_room_booking.services;

import com.example.conference_room_booking.dto.request.BookingRequest;
import com.example.conference_room_booking.entity.Booking;
import com.example.conference_room_booking.entity.BookingStatus;
import com.example.conference_room_booking.entity.Employee;
import com.example.conference_room_booking.entity.Room;
import com.example.conference_room_booking.repository.BookingRepository;
import com.example.conference_room_booking.repository.EmployeeRepository;
import com.example.conference_room_booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    //Booking Available Room
    public void bookRoom(BookingRequest request) {

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Check if slot is already booked
        List<Booking> overlappingBookings =
                bookingRepository.findOverlappingBookings(
                        request.getRoomId(),
                        request.getStartTime(),
                        request.getEndTime());

        if (!overlappingBookings.isEmpty()) {
            throw new RuntimeException("Slot already booked!");
        }

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setEmployee(employee);
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setPurpose(request.getPurpose());
        booking.setStatus(BookingStatus.IN_PROCESS);

        bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

}
