package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Task entity with priority support.
 *
 * TODO: Add priority field and default priority in @PrePersist
 */
@Entity
public class Task extends PanacheEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private Boolean completed;

    // TODO: Add priority field
    // Hint: private String priority;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (completed == null) {
            completed = false;
        }
        // TODO: Set default priority if not set
        // Hint: This will be set from configuration in the service layer
        // For now, you can set a default like "MEDIUM"
        // if (priority == null) {
        //     priority = "MEDIUM";
        // }
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // TODO: Add getter and setter for priority
    // public String getPriority() {
    //     return priority;
    // }
    //
    // public void setPriority(String priority) {
    //     this.priority = priority;
    // }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
