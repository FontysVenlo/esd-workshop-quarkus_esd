package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Task;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service layer for task-related business logic and configuration.
 *
 * TODO: Complete this service class by:
 * 1. Adding @ApplicationScoped annotation
 * 2. Injecting configuration properties
 * 3. Implementing business logic methods
 */
// TODO: Add @ApplicationScoped annotation to make this a CDI bean
public class TaskService {

    // TODO: Inject default priority from configuration
    // @ConfigProperty(name = "task.priority.default")
    // String defaultPriority;

    // TODO: Inject allowed priorities from configuration
    // @ConfigProperty(name = "task.priority.allowed")
    // String allowedPriorities;

    /**
     * Get task statistics including counts and completion rate.
     *
     * TODO: Implement this method to calculate:
     * - Total task count
     * - Completed task count
     * - Pending task count
     * - Completion rate (percentage)
     * - Count by priority (HIGH, MEDIUM, LOW)
     *
     * @return Map with statistics
     */
    public Map<String, Object> getTaskStatistics() {
        // TODO: Get all tasks from database
        // List<Task> allTasks = Task.listAll();

        // TODO: Calculate statistics
        // long total = allTasks.size();
        // long completed = allTasks.stream().filter(Task::getCompleted).count();
        // long pending = total - completed;
        // double completionRate = total > 0 ? (completed * 100.0 / total) : 0.0;

        // TODO: Count tasks by priority
        // Map<String, Long> byPriority = new HashMap<>();
        // for (Task task : allTasks) {
        //     String priority = task.getPriority();
        //     byPriority.put(priority, byPriority.getOrDefault(priority, 0L) + 1);
        // }

        // TODO: Build result map
        // Map<String, Object> stats = new HashMap<>();
        // stats.put("total", total);
        // stats.put("completed", completed);
        // stats.put("pending", pending);
        // stats.put("completionRate", Math.round(completionRate * 100.0) / 100.0);
        // stats.put("byPriority", byPriority);

        // TODO: Return stats
        // return stats;

        return new HashMap<>(); // Placeholder - replace with actual implementation
    }

    /**
     * Get the default priority from configuration.
     *
     * TODO: Implement this method
     * @return Default priority string
     */
    public String getDefaultPriority() {
        // TODO: Return the configured default priority
        // return defaultPriority;
        return "MEDIUM"; // Placeholder
    }

    /**
     * Validate if a priority is allowed.
     *
     * TODO: Implement this method
     * @param priority Priority to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidPriority(String priority) {
        // TODO: Check if priority is in the allowed list
        // Hint: allowedPriorities is a comma-separated string like "LOW,MEDIUM,HIGH"
        // You can use: allowedPriorities.contains(priority)
        // or split and check: Arrays.asList(allowedPriorities.split(",")).contains(priority)

        return true; // Placeholder
    }
}
