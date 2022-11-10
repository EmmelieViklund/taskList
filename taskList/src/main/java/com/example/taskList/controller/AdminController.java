package com.example.taskList.controller;


import com.example.taskList.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin (origins = "http://localhost:8080" )
@RestController
@RequestMapping ("/admin")

public class AdminController {

    @Autowired
    AdminRepository adminRepository;


}
