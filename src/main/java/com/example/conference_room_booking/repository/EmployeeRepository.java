package com.example.conference_room_booking.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.conference_room_booking.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeCode(String employeeCode);
    Optional<Employee> findByEmail(String email);
    boolean existsByEmployeeCode(String employeeCode);
    boolean existsByEmail(String email);
}

