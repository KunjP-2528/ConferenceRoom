package com.example.conference_room_booking.services;

import com.example.conference_room_booking.repository.DepartmentRepository;
import com.example.conference_room_booking.repository.EmployeeRepository;
import com.example.conference_room_booking.entity.Department;
import com.example.conference_room_booking.entity.Employee;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public String uploadEmployeeCSV(MultipartFile file) {

        List<Employee> employeeList = new ArrayList<>();

        try (CSVReader csvReader =
                     new CSVReader(new InputStreamReader(file.getInputStream()))) {

            String[] line;

            csvReader.readNext(); // Skip header

            while ((line = csvReader.readNext()) != null) {

                Employee employee = new Employee();

                employee.setName(line[0]);
                employee.setEmail(line[1]);
                employee.setEmployeeCode(line[2]);

                Long departmentId = Long.parseLong(line[3]);

                Department department = departmentRepository
                        .findById(departmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Department not found with id " + departmentId));

                employee.setDepartment(department);

                employeeList.add(employee);
            }

            employeeRepository.saveAll(employeeList);

            return "CSV uploaded successfully and employees saved!";

        }  catch (Exception e) {
        e.printStackTrace();   // 👈 This will show real error
        throw new RuntimeException("Error processing CSV file: " + e.getMessage());
    }

}
    }

