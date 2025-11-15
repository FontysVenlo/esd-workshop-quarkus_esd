package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Task;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service layer for task-related business logic and configuration.
 */
@ApplicationScoped
public class TaskService {

    @ConfigProperty(name = "task.priority.default")
    String defaultPriority;

    @ConfigProperty(name = "task.priority.allowed")
    String allowedPriorities;

    /**
     * Get task statistics including counts and completion rate.
     *
     * @return Map with statistics
     */
    public Map<String, Object> getTaskStatistics() {
        List<Task> allTasks = Task.listAll();

        long total = allTasks.size();
        long completed = allTasks.stream().filter(Task::getCompleted).count();
        long pending = total - completed;
        double completionRate = total > 0 ? (completed * 100.0 / total) : 0.0;

        // Count tasks by priority
        Map<String, Long> byPriority = new HashMap<>();
        for (Task task : allTasks) {
            String priority = task.getPriority();
            byPriority.put(priority, byPriority.getOrDefault(priority, 0L) + 1);
        }

        // Build result map
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("completed", completed);
        stats.put("pending", pending);
        stats.put("completionRate", Math.round(completionRate * 100.0) / 100.0);
        stats.put("byPriority", byPriority);

        return stats;
    }

    /**
     * Get the default priority from configuration.
     *
     * @return Default priority string
     */
    public String getDefaultPriority() {
        return defaultPriority;
    }

    /**
     * Validate if a priority is allowed.
     *
     * @param priority Priority to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidPriority(String priority) {
        return Arrays.asList(allowedPriorities.split(",")).contains(priority);
    }
}
