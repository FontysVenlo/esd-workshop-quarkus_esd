package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Task;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/**
 * Custom health check for the Task Management System.
 *
 * TODO: Complete this health check by:
 * 1. Adding required annotations
 * 2. Injecting configuration
 * 3. Implementing the health check logic
 */
// TODO: Add @Liveness annotation to register as a liveness check
// TODO: Add @ApplicationScoped annotation
public class TaskHealthCheck implements HealthCheck {

    // TODO: Inject max task count from configuration
    // @ConfigProperty(name = "task.health.max.count", defaultValue = "1000")
    // int maxTaskCount;

    /**
     * Perform health check.
     *
     * TODO: Implement this method to:
     * 1. Get current task count from database
     * 2. Check if count is below max threshold
     * 3. Return UP if healthy, DOWN if not
     * 4. Include task count and max count in response data
     *
     * @return HealthCheckResponse with status and data
     */
    @Override
    public HealthCheckResponse call() {
        // TODO: Get current task count
        // long taskCount = Task.count();

        // TODO: Check if task count is below max
        // boolean isHealthy = taskCount < maxTaskCount;

        // TODO: Build health check response
        // HealthCheckResponse response = HealthCheckResponse
        //     .named("Task Database Health")
        //     .status(isHealthy)
        //     .withData("taskCount", taskCount)
        //     .withData("maxAllowed", maxTaskCount)
        //     .build();

        // TODO: Return the response
        // return response;

        // Placeholder - replace with actual implementation
        return HealthCheckResponse
            .named("Task Database Health")
            .up()
            .build();
    }
}
