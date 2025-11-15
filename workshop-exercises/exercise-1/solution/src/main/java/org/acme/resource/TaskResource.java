package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * REST Resource for managing tasks.
 */
@Path("/api/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    // In-memory storage for tasks
    private final List<Task> tasks = new ArrayList<>();

    // ID generator
    private final AtomicLong idGenerator = new AtomicLong(0);

    /**
     * Get all tasks
     *
     * @return List of all tasks
     */
    @GET
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Get a task by ID
     *
     * @param id The task ID
     * @return Response with the task or 404
     */
    @GET
    @Path("{id}")
    public Response getTaskById(@PathParam("id") Long id) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        return task
                .map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Create a new task
     *
     * @param task The task to create (without ID)
     * @return Response with HTTP 201 and the created task
     */
    @POST
    public Response createTask(Task task) {
        Long newId = idGenerator.incrementAndGet();
        task.setId(newId);
        tasks.add(task);
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    /**
     * Update an existing task
     *
     * @param id The task ID
     * @param updatedTask The updated task data
     * @return Response with the updated task or 404
     */
    @PUT
    @Path("{id}")
    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
        Optional<Task> existingTask = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.getCompleted());
            return Response.ok(task).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * Delete a task
     *
     * @param id The task ID
     * @return Response with HTTP 204 or 404
     */
    @DELETE
    @Path("{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));

        if (removed) {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
