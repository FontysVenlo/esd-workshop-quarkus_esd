package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Task;

import java.util.List;

/**
 * REST Resource for managing tasks with database persistence.
 */
@Path("/api/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    /**
     * Get all tasks from database
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
     * @param id The task ID
     * @return Response with the task or 404
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
     * @param status The completion status
     * @return List of filtered tasks
     */
    @GET
    @Path("/completed/{status}")
    public List<Task> getTasksByStatus(@PathParam("status") Boolean status) {
        return Task.find("completed", status).list();
    }

    /**
     * Create a new task in database
     *
     * @param task The task to create
     * @return Response with HTTP 201 and the created task
     */
    @POST
    @Transactional
    public Response createTask(Task task) {
        task.persist();
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    /**
     * Update an existing task in database
     *
     * @param id The task ID
     * @param updatedTask The updated task data
     * @return Response with updated task or 404
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
            // No need to call persist() - @Transactional handles it!
            return Response.ok(task).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Delete a task from database
     *
     * @param id The task ID
     * @return Response with HTTP 204 or 404
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
