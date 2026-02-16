package com.example.conference_room_booking.repository;

import com.example.conference_room_booking.entity.Booking;
import com.example.conference_room_booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {



    @Query("""
SELECT b FROM Booking b
WHERE b.room.id = :roomId
AND b.startTime < :endTime
AND b.endTime > :startTime
""")
    List<Booking> findOverlappingBookings(
            Long roomId,
            LocalDateTime startTime,
            LocalDateTime endTime);
}
