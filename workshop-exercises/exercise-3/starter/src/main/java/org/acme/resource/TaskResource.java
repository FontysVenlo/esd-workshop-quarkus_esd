package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Task;
import org.acme.service.TaskService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

/**
 * REST Resource for managing tasks with OpenAPI documentation.
 *
 * TODO: Add OpenAPI annotations for better API documentation
 */
// TODO: Add @Tag annotation to categorize this resource in Swagger UI
// @Tag(name = "Task Management", description = "Operations for managing tasks")
@Path("/api/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    // TODO: Inject TaskService
    // @Inject
    // TaskService taskService;

    /**
     * Get all tasks from database
     *
     * TODO: Add OpenAPI annotations:
     * @Operation(summary = "Get all tasks", description = "Returns all tasks from the database")
     * @APIResponse(responseCode = "200", description = "Success")
     *
     * @return List of all tasks
     */
    @GET
    public List<Task> getAllTasks() {
        return Task.listAll();
    }

    /**
     * Get a task by ID from database
     *
     * TODO: Add OpenAPI annotations:
     * @Operation(summary = "Get task by ID", description = "Returns a single task by its ID")
     * @APIResponse(responseCode = "200", description = "Task found")
     * @APIResponse(responseCode = "404", description = "Task not found")
     */
    @GET
    @Path("{id}")
    public Response getTaskById(@PathParam("id") Long id) {
        Task task = Task.findById(id);

        if (task != null) {
            return Response.ok(task).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Get tasks filtered by completion status
     *
     * TODO: Add OpenAPI annotations
     */
    @GET
    @Path("/completed/{status}")
    public List<Task> getTasksByStatus(@PathParam("status") Boolean status) {
        return Task.find("completed", status).list();
    }

    /**
     * Get tasks filtered by priority
     *
     * TODO: Implement this method
     * 1. Add @GET and @Path("/priority/{priority}") annotations
     * 2. Use Task.find("priority", priority).list()
     * 3. Add OpenAPI annotations
     *
     * @param priority The priority level (HIGH, MEDIUM, LOW)
     * @return List of tasks with the specified priority
     */
    // @GET
    // @Path("/priority/{priority}")
    // @Operation(summary = "Get tasks by priority", description = "Returns tasks filtered by priority level")
    // @APIResponse(responseCode = "200", description = "Success")
    public List<Task> getTasksByPriority(@PathParam("priority") String priority) {
        // TODO: Implement
        // return Task.find("priority", priority).list();
        return null;
    }

    /**
     * Get task statistics
     *
     * TODO: Implement this method
     * 1. Add @GET and @Path("/stats") annotations
     * 2. Inject and use TaskService to get statistics
     * 3. Return the statistics map
     * 4. Add OpenAPI annotations
     *
     * @return Map with task statistics
     */
    // @GET
    // @Path("/stats")
    // @Operation(summary = "Get task statistics", description = "Returns statistics about tasks including counts and completion rate")
    // @APIResponse(responseCode = "200", description = "Success")
    public Map<String, Object> getStatistics() {
        // TODO: Use taskService to get statistics
        // return taskService.getTaskStatistics();
        return null;
    }

    /**
     * Create a new task in database
     *
     * TODO: Add OpenAPI annotations
     * TODO: Set default priority if not provided using TaskService
     */
    @POST
    @Transactional
    public Response createTask(Task task) {
        // TODO: If priority is not set, use default from configuration
        // if (task.getPriority() == null) {
        //     task.setPriority(taskService.getDefaultPriority());
        // }

        task.persist();
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    /**
     * Update an existing task in database
     *
     * TODO: Add OpenAPI annotations
     */
    @PUT
    @Path("{id}")
    @Transactional
    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
        Task task = Task.findById(id);

        if (task != null) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.getCompleted());

            // TODO: Update priority if provided
            // if (updatedTask.getPriority() != null) {
            //     task.setPriority(updatedTask.getPriority());
            // }

            return Response.ok(task).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Delete a task from database
     *
     * TODO: Add OpenAPI annotations
     */
    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteTask(@PathParam("id") Long id) {
        Task task = Task.findById(id);

        if (task != null) {
            task.delete();
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
