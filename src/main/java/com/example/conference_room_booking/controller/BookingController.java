package com.example.conference_room_booking.controller;

import com.example.conference_room_booking.dto.request.BookingRequest;
import com.example.conference_room_booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @PostMapping("/book")
    public ResponseEntity<String> bookRoom(@RequestBody BookingRequest request) {
        bookingService.bookRoom(request);
        return ResponseEntity.ok("Room booked successfully");
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled");
    }
}
