package com.example.taskList.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "customerName")
    private String customerName;
    @Column(name = "GlobalCaseId")
    private String globalCaseId;
    @Column(name = "taskType")
    private String taskType;

    @Column(name = "caseLogs")
    private String caseLogs;

    @Column(nullable = false)
    private boolean isMarkedDone = false;

    @CreationTimestamp
    @JsonFormat(pattern = "yy-MM-dd hh:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yy-MM-dd hh:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @JsonFormat(pattern = "yy-MM-dd hh:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "expires_at", nullable = false, updatable = false)
    private Date expiresAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Employee employee;

    public Task() {
    }

    public Task(Long id, String customerName, String globalCaseId, String taskType, String caseLogs, Date createdAt, LocalDateTime updatedAt, Date expiresAt, Employee employee) {
        this.id = id;
        this.customerName = customerName;
        this.globalCaseId = globalCaseId;
        this.taskType = taskType;
        this.caseLogs = caseLogs;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGlobalCaseId() {
        return globalCaseId;
    }

    public void setGlobalCaseId(String globalCaseId) {
        this.globalCaseId = globalCaseId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getCaseLogs() {
        return caseLogs;
    }

    public void setCaseLogs(String caseLogs) {
        this.caseLogs = caseLogs;
    }

    public boolean isMarkedDone() {
        return isMarkedDone;
    }

    public void setMarkedDone(boolean markedDone) {
        isMarkedDone = markedDone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", globalCaseId='" + globalCaseId + '\'' +
                ", taskType='" + taskType + '\'' +
                ", caseLogs='" + caseLogs + '\'' +
                ", isMarkedDone=" + isMarkedDone +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", expiresAt=" + expiresAt +
                ", employee=" + employee +
                '}';
    }
}
