package com.example.taskList.repo;

import com.example.taskList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /*List<Employee> findByAdminId(Long adminId);*/
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
