package com.example.taskList.Service;

import com.example.taskList.model.User;
import com.example.taskList.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    public User fetchUserByUsernameAndPassword (String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }
}
