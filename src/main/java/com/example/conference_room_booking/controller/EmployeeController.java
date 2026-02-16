package com.example.conference_room_booking.controller;




import com.example.conference_room_booking.entity.Employee;
import com.example.conference_room_booking.repository.EmployeeRepository;
import com.example.conference_room_booking.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
@Autowired
private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/csv-Upload")
    public ResponseEntity<String> uploadCSV(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        String message = employeeService.uploadEmployeeCSV(file);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/get")
    public List<Employee> GetAlluser(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }





}