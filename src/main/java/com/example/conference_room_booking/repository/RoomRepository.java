package com.example.conference_room_booking.repository;

import com.example.conference_room_booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.conference_room_booking.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("""
            SELECT r FROM Room r
            WHERE r.id NOT IN (
                SELECT b.room.id FROM Booking b
                WHERE b.startTime < :endTime
                AND b.endTime > :startTime
            )
            """)
    List<Room> findAvailableRooms(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime);
}
