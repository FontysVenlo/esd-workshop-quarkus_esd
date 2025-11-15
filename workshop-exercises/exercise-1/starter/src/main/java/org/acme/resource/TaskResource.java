package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * REST Resource for managing tasks.
 *
 * TODO: Implement the CRUD operations for Task management
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
     * TODO: Implement this method
     * 1. Add @GET annotation
     * 2. Return the list of all tasks
     *
     * @return List of all tasks
     */
    // @GET
    public List<Task> getAllTasks() {
        // TODO: Return the tasks list
        return null;
    }

    /**
     * Get a task by ID
     *
     * TODO: Implement this method
     * 1. Add @GET annotation
     * 2. Add @Path("{id}") annotation to handle /api/tasks/{id}
     * 3. Find the task with the given ID
     * 4. Return Response.ok(task) if found
     * 5. Return Response.status(Response.Status.NOT_FOUND).build() if not found
     *
     * @param id The task ID
     * @return Response with the task or 404
     */
    // @GET
    // @Path("{id}")
    public Response getTaskById(@PathParam("id") Long id) {
        // TODO: Find the task by ID
        // Hint: Use tasks.stream().filter(t -> t.getId().equals(id)).findFirst()

        // TODO: Return Response.ok(task) if found, or Response.status(404).build() if not found
        return null;
    }

    /**
     * Create a new task
     *
     * TODO: Implement this method
     * 1. Add @POST annotation
     * 2. Generate a new ID using idGenerator.incrementAndGet()
     * 3. Set the ID on the task
     * 4. Add the task to the tasks list
     * 5. Return Response.status(Response.Status.CREATED).entity(task).build()
     *
     * @param task The task to create (without ID)
     * @return Response with HTTP 201 and the created task
     */
    // @POST
    public Response createTask(Task task) {
        // TODO: Generate new ID and set it on the task
        // Long newId = idGenerator.incrementAndGet();
        // task.setId(newId);

        // TODO: Add task to the list
        // tasks.add(task);

        // TODO: Return HTTP 201 with the created task
        // return Response.status(Response.Status.CREATED).entity(task).build();
        return null;
    }

    /**
     * Update an existing task
     *
     * TODO: Implement this method
     * 1. Add @PUT annotation
     * 2. Add @Path("{id}") annotation
     * 3. Find the existing task by ID
     * 4. Update the task's fields (title, description, completed)
     * 5. Return Response.ok(updatedTask) if found
     * 6. Return Response.status(Response.Status.NOT_FOUND).build() if not found
     *
     * @param id The task ID
     * @param updatedTask The updated task data
     * @return Response with the updated task or 404
     */
    // @PUT
    // @Path("{id}")
    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
        // TODO: Find the task by ID
        // Use: tasks.stream().filter(t -> t.getId().equals(id)).findFirst()

        // TODO: If found, update the task fields
        // existingTask.setTitle(updatedTask.getTitle());
        // existingTask.setDescription(updatedTask.getDescription());
        // existingTask.setCompleted(updatedTask.getCompleted());

        // TODO: Return Response.ok(existingTask) if found, or Response.status(404).build() if not found
        return null;
    }

    /**
     * Delete a task
     *
     * TODO: Implement this method
     * 1. Add @DELETE annotation
     * 2. Add @Path("{id}") annotation
     * 3. Find and remove the task by ID
     * 4. Return Response.noContent().build() if deleted (HTTP 204)
     * 5. Return Response.status(Response.Status.NOT_FOUND).build() if not found
     *
     * @param id The task ID
     * @return Response with HTTP 204 or 404
     */
    // @DELETE
    // @Path("{id}")
    public Response deleteTask(@PathParam("id") Long id) {
        // TODO: Remove the task by ID
        // boolean removed = tasks.removeIf(t -> t.getId().equals(id));

        // TODO: Return Response.noContent().build() if removed, or Response.status(404).build() if not found
        return null;
    }
}
