package com.example.taskList.controller;

import com.example.taskList.enumeration.ResponsePhrases;
import com.example.taskList.exception.ResourceNotFoundException;
import com.example.taskList.model.Employee;
import com.example.taskList.repo.AdminRepository;
import com.example.taskList.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.taskList.enumeration.ResponsePhrases.*;

@CrossOrigin(origins = "http://localhost:8080" )
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    //Gets all employees by admin Id
    @GetMapping("/administrators/{adminId}/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesByAdministratorId(@PathVariable(value = "adminId") Long adminId) {
        if (!adminRepo.existsById(adminId)) {
            throw new ResourceNotFoundException(NO_EMPLOYEES_RESPONDS_To_Admin + adminId);
        }

        List<Employee> employees = employeeRepo.findByAdminId(adminId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //find employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable(value = "id") long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_EMPLOYEES_WITH_ID + id));

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    //create employee by admin id
    @PostMapping("/administrators/{adminId}/employees")
    public ResponseEntity<Employee> createEmployeeByAdminId(@PathVariable(value = "adminId") Long adminId,
                                                            @RequestBody Employee employeeRequest) {

        Employee employee = adminRepo.findById(adminId).map(admin -> {
            employeeRequest.setAdmin(admin);
            return employeeRepo.save(employeeRequest);
        }).orElseThrow(() -> new ResourceNotFoundException(SOMETHING_WENT_WRONG + adminId));

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    //create employee by admin id
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id, @RequestBody Employee employeeRequest) {
        Employee _employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_EMPLOYEES_WITH_ID + id));

        _employee.setUsername(employeeRequest.getUsername());
        _employee.setPassword(employeeRequest.getPassword());
        _employee.setBusinessGroup(employeeRequest.getBusinessGroup());

        return new ResponseEntity<>(employeeRepo.save(_employee), HttpStatus.OK);
    }

    //delete employee by id
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id ) {
        employeeRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
