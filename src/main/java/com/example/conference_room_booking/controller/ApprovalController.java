package com.example.conference_room_booking.controller;

import com.example.conference_room_booking.services.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;


    @PutMapping("/{bookingId}/approve")
    public ResponseEntity<String> approveBooking(
            @PathVariable Long bookingId,
            @RequestParam String approvedBy,
            @RequestParam String remark) {

        approvalService.approveBooking(bookingId, approvedBy, remark);
        return ResponseEntity.ok("Booking approved");
    }

    @PutMapping("/{bookingId}/reject")
    public ResponseEntity<String> rejectBooking(
            @PathVariable Long bookingId,
            @RequestParam String approvedBy,
            @RequestParam String remark) {

        approvalService.rejectBooking(bookingId, approvedBy, remark);
        return ResponseEntity.ok("Booking rejected");
    }
}