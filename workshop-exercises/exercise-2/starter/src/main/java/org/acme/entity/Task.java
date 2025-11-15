package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Task entity using Panache Active Record pattern.
 *
 * TODO: Complete this entity class by:
 * 1. Adding @Entity annotation to mark this as a JPA entity
 * 2. Making this class extend PanacheEntity (gives you 'id' for free!)
 * 3. Adding the required fields with appropriate annotations
 * 4. Adding a @PrePersist method to set createdAt timestamp
 *
 * Note: PanacheEntity provides:
 * - public Long id (auto-generated primary key)
 * - persist() method to save/update
 * - delete() method to remove
 * - Static methods: findAll(), findById(), find(), list(), count(), etc.
 */
// TODO: Add @Entity annotation
public class Task /* TODO: extends PanacheEntity */ {

    // TODO: Add field: private String title;
    // Hint: Use @Column annotation to specify NOT NULL constraint
    // @Column(nullable = false)

    // TODO: Add field: private String description;
    // Hint: Use @Column with length for longer text
    // @Column(length = 1000)

    // TODO: Add field: private Boolean completed;
    // Hint: Set default value to false

    // TODO: Add field: private LocalDateTime createdAt;

    // TODO: Add a @PrePersist method to set createdAt before saving
    // This method is called automatically before the entity is persisted
    // @PrePersist
    // public void prePersist() {
    //     if (createdAt == null) {
    //         createdAt = LocalDateTime.now();
    //     }
    //     if (completed == null) {
    //         completed = false;
    //     }
    // }

    // Getters and Setters
    // TODO: Add getters and setters for all fields
    // Note: You don't need getId() and setId() - PanacheEntity provides them!

    // Example:
    // public String getTitle() {
    //     return title;
    // }
    //
    // public void setTitle(String title) {
    //     this.title = title;
    // }
}
