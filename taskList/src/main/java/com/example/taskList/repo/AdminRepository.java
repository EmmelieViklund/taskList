package com.example.taskList.repo;

import com.example.taskList.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /*Optional<Admin> findById(Long id);

    void deleteById(Long id);*/
}
