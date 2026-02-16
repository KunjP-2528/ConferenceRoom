package com.example.conference_room_booking.services;

import com.example.conference_room_booking.entity.Approval;
import com.example.conference_room_booking.entity.Booking;
import com.example.conference_room_booking.entity.BookingStatus;
import com.example.conference_room_booking.repository.ApprovalRepository;
import com.example.conference_room_booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApprovalService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    public void approveBooking(Long bookingId, String approvedBy, String remark) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Only pending bookings can be approved");
        }

        booking.setStatus(BookingStatus.APPROVED);

        Approval approval = new Approval();
        approval.setBooking(booking);
        approval.setApprovedBy(approvedBy);
        approval.setRemark(remark);
        approval.setApprovalTime(LocalDateTime.now());

        bookingRepository.save(booking);
        approvalRepository.save(approval);
    }
    public void rejectBooking(Long bookingId, String approvedBy, String remark) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Only pending bookings can be approved");
        }

        booking.setStatus(BookingStatus.REJECTED);

        Approval approval = new Approval();
        approval.setBooking(booking);
        approval.setApprovedBy(approvedBy);
        approval.setRemark(remark);
        approval.setApprovalTime(LocalDateTime.now());

        bookingRepository.save(booking);
        approvalRepository.save(approval);
    }

}
