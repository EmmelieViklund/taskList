package com.example.taskList.repo;

import com.example.taskList.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Long, Admin> {

}
