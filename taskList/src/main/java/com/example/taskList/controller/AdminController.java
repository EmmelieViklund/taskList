package com.example.taskList.controller;


import com.example.taskList.exception.ResourceNotFoundException;
import com.example.taskList.model.Admin;
import com.example.taskList.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.example.taskList.enumeration.ResponsePhrases.*;

@Controller
@RequestMapping ("/admin")
public class AdminController {
    @Autowired
    AdminRepository adminRepo;

    //Create Admin
    @PostMapping("/administrators")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin _admin = adminRepo.save(new Admin(admin.getUsername(), admin.getPassword()));
        return new ResponseEntity<>(_admin, HttpStatus.CREATED);
    }

    //Finds Admin by Id
    @GetMapping("/administrators/{id}")
    public ResponseEntity<Admin> getAdministratorById(@PathVariable("id") Long id) {
        Admin admin = adminRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_ADMIN_WITH_ID + id));
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    //Update Admin
    @PutMapping("/administrators/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable(value ="id") Long id, @RequestBody Admin admin) {
        Admin _admin = adminRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_ADMIN_WITH_ID + id + WAS_FOUND));
        _admin.setUsername(admin.getUsername());
        _admin.setPassword(admin.getPassword());

        return new ResponseEntity<>(adminRepo.save(_admin), HttpStatus.OK);
    }

    //Delete Admin by id
    @DeleteMapping("/administrators/{id}")
    public ResponseEntity<HttpStatus> deleteAdministrator(@PathVariable("id") Long id) {
        adminRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
