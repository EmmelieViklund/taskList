package com.example.taskList.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "App_User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employee_generator")
    private Long id;

    @Column(name="username", nullable = false, updatable = false)
    private String username;

    @Column(name="password", nullable = false, updatable = false)
    private String password;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @CreationTimestamp
    @JsonFormat(pattern = "yy-MM-dd hh:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, boolean isAdmin, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", createdAt=" + createdAt +
                '}';
    }
}