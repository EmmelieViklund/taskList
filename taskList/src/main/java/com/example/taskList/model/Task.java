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
    @Column(name = "prioritizationType")
    private String prioritizationType;

    @Column(name = "caseNotes")
    private String caseNotes;
    @UpdateTimestamp
    @JsonFormat(pattern = "yy-MM-dd hh:mm", shape = JsonFormat.Shape.STRING)
    @Column(name = "completion_At")
    private LocalDateTime completionAt;

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
    @JoinColumn(name = "user_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Task() {
    }

    public Task(Long id, String prioritizationType, String caseNotes, LocalDateTime completionAt, Date createdAt, LocalDateTime updatedAt, Date expiresAt, User user) {
        this.id = id;
        this.prioritizationType = prioritizationType;
        this.caseNotes = caseNotes;
        this.completionAt = completionAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrioritizationType() {
        return prioritizationType;
    }

    public void setPrioritizationType(String prioritizationType) {
        this.prioritizationType = prioritizationType;
    }

    public String getCaseNotes() {
        return caseNotes;
    }

    public void setCaseNotes(String caseNotes) {
        this.caseNotes = caseNotes;
    }

    public LocalDateTime getCompletionAt() {
        return completionAt;
    }

    public void setCompletionAt(LocalDateTime completionAt) {
        this.completionAt = completionAt;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", prioritizationType='" + prioritizationType + '\'' +
                ", caseNotes='" + caseNotes + '\'' +
                ", completionAt=" + completionAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", expiresAt=" + expiresAt +
                ", user=" + user +
                '}';
    }
}
