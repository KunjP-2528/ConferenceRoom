package com.example.conference_room_booking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.conference_room_booking.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
