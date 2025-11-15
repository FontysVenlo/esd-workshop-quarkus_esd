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
 */
@Tag(name = "Task Management", description = "Operations for managing tasks")
@Path("/api/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskService taskService;

    /**
     * Get all tasks from database
     */
    @GET
    @Operation(summary = "Get all tasks", description = "Returns all tasks from the database")
    @APIResponse(responseCode = "200", description = "Success")
    public List<Task> getAllTasks() {
        return Task.listAll();
    }

    /**
     * Get a task by ID from database
     */
    @GET
    @Path("{id}")
    @Operation(summary = "Get task by ID", description = "Returns a single task by its ID")
    @APIResponse(responseCode = "200", description = "Task found")
    @APIResponse(responseCode = "404", description = "Task not found")
    public Response getTaskById(@PathParam("id") Long id) {
        Task task = Task.findById(id);

        if (task != null) {
            return Response.ok(task).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Get tasks filtered by completion status
     */
    @GET
    @Path("/completed/{status}")
    @Operation(summary = "Get tasks by completion status", description = "Returns tasks filtered by completion status")
    @APIResponse(responseCode = "200", description = "Success")
    public List<Task> getTasksByStatus(@PathParam("status") Boolean status) {
        return Task.find("completed", status).list();
    }

    /**
     * Get tasks filtered by priority
     */
    @GET
    @Path("/priority/{priority}")
    @Operation(summary = "Get tasks by priority", description = "Returns tasks filtered by priority level")
    @APIResponse(responseCode = "200", description = "Success")
    public List<Task> getTasksByPriority(@PathParam("priority") String priority) {
        return Task.find("priority", priority).list();
    }

    /**
     * Get task statistics
     */
    @GET
    @Path("/stats")
    @Operation(summary = "Get task statistics", description = "Returns statistics about tasks including counts and completion rate")
    @APIResponse(responseCode = "200", description = "Success")
    public Map<String, Object> getStatistics() {
        return taskService.getTaskStatistics();
    }

    /**
     * Create a new task in database
     */
    @POST
    @Transactional
    @Operation(summary = "Create a new task", description = "Creates a new task in the database")
    @APIResponse(responseCode = "201", description = "Task created")
    public Response createTask(Task task) {
        if (task.getPriority() == null) {
            task.setPriority(taskService.getDefaultPriority());
        }

        task.persist();
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    /**
     * Update an existing task in database
     */
    @PUT
    @Path("{id}")
    @Transactional
    @Operation(summary = "Update a task", description = "Updates an existing task by ID")
    @APIResponse(responseCode = "200", description = "Task updated")
    @APIResponse(responseCode = "404", description = "Task not found")
    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
        Task task = Task.findById(id);

        if (task != null) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.getCompleted());

            if (updatedTask.getPriority() != null) {
                task.setPriority(updatedTask.getPriority());
            }

            return Response.ok(task).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Delete a task from database
     */
    @DELETE
    @Path("{id}")
    @Transactional
    @Operation(summary = "Delete a task", description = "Deletes a task by ID")
    @APIResponse(responseCode = "204", description = "Task deleted")
    @APIResponse(responseCode = "404", description = "Task not found")
    public Response deleteTask(@PathParam("id") Long id) {
        Task task = Task.findById(id);

        if (task != null) {
            task.delete();
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
