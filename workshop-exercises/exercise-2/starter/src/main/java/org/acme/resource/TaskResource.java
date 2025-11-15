package org.acme.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Task;

import java.util.List;

/**
 * REST Resource for managing tasks with database persistence.
 *
 * TODO: Implement CRUD operations using Panache Entity methods
 *
 * Important: Write operations (POST, PUT, DELETE) MUST be annotated with @Transactional!
 */
@Path("/api/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    /**
     * Get all tasks from database
     *
     * TODO: Implement this method
     * 1. Add @GET annotation
     * 2. Use Task.listAll() to get all tasks from database
     * 3. Return the list
     *
     * @return List of all tasks
     */
    // @GET
    public List<Task> getAllTasks() {
        // TODO: Use Panache method to get all tasks
        // return Task.listAll();
        return null;
    }

    /**
     * Get a task by ID from database
     *
     * TODO: Implement this method
     * 1. Add @GET and @Path("{id}") annotations
     * 2. Use Task.findById(id) to find the task
     * 3. Return Response.ok(task) if found
     * 4. Return Response.status(404).build() if not found (null)
     *
     * @param id The task ID
     * @return Response with the task or 404
     */
    // @GET
    // @Path("{id}")
    public Response getTaskById(@PathParam("id") Long id) {
        // TODO: Find task by ID using Panache
        // Task task = Task.findById(id);

        // TODO: Return appropriate response
        // if (task != null) {
        //     return Response.ok(task).build();
        // }
        // return Response.status(Response.Status.NOT_FOUND).build();
        return null;
    }

    /**
     * Get tasks filtered by completion status
     *
     * TODO: Implement this method
     * 1. Add @GET and @Path("/completed/{status}") annotations
     * 2. Use Task.find("completed", status).list() to filter tasks
     * 3. Return the filtered list
     *
     * @param status The completion status
     * @return List of filtered tasks
     */
    // @GET
    // @Path("/completed/{status}")
    public List<Task> getTasksByStatus(@PathParam("status") Boolean status) {
        // TODO: Use Panache find method to filter by completed status
        // return Task.find("completed", status).list();
        return null;
    }

    /**
     * Create a new task in database
     *
     * TODO: Implement this method
     * 1. Add @POST annotation
     * 2. Add @Transactional annotation (required for write operations!)
     * 3. Use task.persist() to save to database
     * 4. Return Response.status(201).entity(task).build()
     *
     * Note: The @PrePersist method in Task entity will set createdAt automatically
     *
     * @param task The task to create
     * @return Response with HTTP 201 and the created task
     */
    // @POST
    // @Transactional
    public Response createTask(Task task) {
        // TODO: Persist the task to database
        // task.persist();

        // TODO: Return HTTP 201 with created task
        // return Response.status(Response.Status.CREATED).entity(task).build();
        return null;
    }

    /**
     * Update an existing task in database
     *
     * TODO: Implement this method
     * 1. Add @PUT and @Path("{id}") annotations
     * 2. Add @Transactional annotation (required for write operations!)
     * 3. Find the existing task using Task.findById(id)
     * 4. If found, update its fields from updatedTask
     * 5. Return Response.ok(task) - changes are auto-saved due to @Transactional!
     * 6. Return Response.status(404).build() if not found
     *
     * @param id The task ID
     * @param updatedTask The updated task data
     * @return Response with updated task or 404
     */
    // @PUT
    // @Path("{id}")
    // @Transactional
    public Response updateTask(@PathParam("id") Long id, Task updatedTask) {
        // TODO: Find existing task
        // Task task = Task.findById(id);

        // TODO: If found, update fields
        // if (task != null) {
        //     task.setTitle(updatedTask.getTitle());
        //     task.setDescription(updatedTask.getDescription());
        //     task.setCompleted(updatedTask.getCompleted());
        //     // Note: No need to call persist() - @Transactional handles it!
        //     return Response.ok(task).build();
        // }

        // TODO: Return 404 if not found
        // return Response.status(Response.Status.NOT_FOUND).build();
        return null;
    }

    /**
     * Delete a task from database
     *
     * TODO: Implement this method
     * 1. Add @DELETE and @Path("{id}") annotations
     * 2. Add @Transactional annotation (required for write operations!)
     * 3. Find the task using Task.findById(id)
     * 4. If found, call task.delete()
     * 5. Return Response.noContent().build() (HTTP 204)
     * 6. Return Response.status(404).build() if not found
     *
     * @param id The task ID
     * @return Response with HTTP 204 or 404
     */
    // @DELETE
    // @Path("{id}")
    // @Transactional
    public Response deleteTask(@PathParam("id") Long id) {
        // TODO: Find task
        // Task task = Task.findById(id);

        // TODO: Delete if found
        // if (task != null) {
        //     task.delete();
        //     return Response.noContent().build();
        // }

        // TODO: Return 404 if not found
        // return Response.status(Response.Status.NOT_FOUND).build();
        return null;
    }
}
