package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Task;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

/**
 * Custom health check for the Task Management System.
 */
@Liveness
@ApplicationScoped
public class TaskHealthCheck implements HealthCheck {

    @ConfigProperty(name = "task.health.max.count", defaultValue = "1000")
    int maxTaskCount;

    @Override
    public HealthCheckResponse call() {
        long taskCount = Task.count();
        boolean isHealthy = taskCount < maxTaskCount;

        return HealthCheckResponse
            .named("Task Database Health")
            .status(isHealthy)
            .withData("taskCount", taskCount)
            .withData("maxAllowed", maxTaskCount)
            .build();
    }
}
