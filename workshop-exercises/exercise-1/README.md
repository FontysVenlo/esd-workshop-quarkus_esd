# Exercise 1: Basic REST API

**Duration:** 20 minutes

**Difficulty:** Beginner

## Learning Objectives

By completing this exercise, you will learn:
- How to create RESTful endpoints using JAX-RS in Quarkus
- Request and response handling with JSON
- Basic validation
- Testing REST APIs with REST Assured
- Using Quarkus Dev Mode for rapid development

## Overview

In this exercise, you'll build a simple Task Management REST API with basic CRUD operations. The tasks will be stored in memory (no database yet - that's Exercise 2!).

## Task Model

A Task has the following properties:
- `id` (Long): Unique identifier
- `title` (String): Task title
- `description` (String): Detailed description
- `completed` (Boolean): Whether the task is completed

## API Endpoints to Implement

| Method | Path | Description | Request Body | Response |
|--------|------|-------------|--------------|----------|
| GET | /api/tasks | Get all tasks | - | List of tasks |
| GET | /api/tasks/{id} | Get task by ID | - | Single task or 404 |
| POST | /api/tasks | Create new task | Task (without ID) | Created task with ID |
| PUT | /api/tasks/{id} | Update existing task | Task | Updated task or 404 |
| DELETE | /api/tasks/{id} | Delete task | - | 204 No Content or 404 |

## Project Structure

```
starter/
├── src/
│   ├── main/
│   │   ├── java/org/acme/
│   │   │   ├── model/
│   │   │   │   └── Task.java          # TODO: Complete the model
│   │   │   └── resource/
│   │   │       └── TaskResource.java   # TODO: Implement REST endpoints
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/org/acme/
│           └── resource/
│               └── TaskResourceTest.java  # Tests to verify your work
└── pom.xml
```

## Instructions

### Step 1: Set Up the Project

1. Navigate to the starter directory:
   ```bash
   cd workshop-exercises/exercise-1/starter
   ```

2. Start Quarkus in dev mode:
   ```bash
   mvn quarkus:dev
   ```

3. You should see the Quarkus banner and the app running on http://localhost:8080

### Step 2: Complete the Task Model

Open `src/main/java/org/acme/model/Task.java` and complete the TODOs:

1. Add the required fields (id, title, description, completed)
2. Add constructors (no-arg and all-args)
3. Add getters and setters

**Hints:**
- Use appropriate Java types
- Consider which fields are required vs optional
- The ID will be auto-generated in the resource class

### Step 3: Implement REST Endpoints

Open `src/main/java/org/acme/resource/TaskResource.java` and implement:

1. **GET /api/tasks** - Return all tasks
   - Use `@GET` annotation
   - Return the list of tasks

2. **GET /api/tasks/{id}** - Get task by ID
   - Use `@GET` and `@Path("{id}")` annotations
   - Use `@PathParam` to extract the ID
   - Return 404 if task not found

3. **POST /api/tasks** - Create a new task
   - Use `@POST` annotation
   - Generate a new ID for the task
   - Add task to the list
   - Return HTTP 201 (Created) with the task

4. **PUT /api/tasks/{id}** - Update a task
   - Use `@PUT` and `@Path("{id}")` annotations
   - Find and update the existing task
   - Return 404 if task not found

5. **DELETE /api/tasks/{id}** - Delete a task
   - Use `@DELETE` and `@Path("{id}")` annotations
   - Remove task from the list
   - Return 204 (No Content) or 404 if not found

**Key JAX-RS Annotations:**
- `@Path` - Define the resource path
- `@GET`, `@POST`, `@PUT`, `@DELETE` - HTTP methods
- `@PathParam` - Extract path parameters
- `@Produces(MediaType.APPLICATION_JSON)` - Response format
- `@Consumes(MediaType.APPLICATION_JSON)` - Request format

### Step 4: Test Your Implementation

1. **Run the tests:**
   ```bash
   mvn test
   ```
   All tests should pass when your implementation is correct.

2. **Manual testing with cURL:**

   Create a task:
   ```bash
   curl -X POST http://localhost:8080/api/tasks \
     -H "Content-Type: application/json" \
     -d '{"title":"Learn Quarkus","description":"Complete all exercises","completed":false}'
   ```

   Get all tasks:
   ```bash
   curl http://localhost:8080/api/tasks
   ```

   Get task by ID:
   ```bash
   curl http://localhost:8080/api/tasks/1
   ```

   Update a task:
   ```bash
   curl -X PUT http://localhost:8080/api/tasks/1 \
     -H "Content-Type: application/json" \
     -d '{"title":"Learn Quarkus","description":"Complete all exercises","completed":true}'
   ```

   Delete a task:
   ```bash
   curl -X DELETE http://localhost:8080/api/tasks/1
   ```

### Step 5: Explore Quarkus Features

1. **Dev UI:** Visit http://localhost:8080/q/dev
   - Explore the available extensions
   - Check configuration
   - View build information

2. **Live Reload:**
   - Make a change to your code (e.g., change a response message)
   - Save the file
   - The application automatically reloads!
   - Test the endpoint again to see your changes

3. **Continuous Testing:**
   - Press `r` in the terminal running `mvn quarkus:dev`
   - Tests run automatically on code changes

## Expected Behavior

- Creating a task returns HTTP 201 with the created task (with generated ID)
- Getting all tasks returns HTTP 200 with a JSON array
- Getting a task by ID returns HTTP 200 with the task, or 404 if not found
- Updating a task returns HTTP 200 with the updated task, or 404 if not found
- Deleting a task returns HTTP 204, or 404 if not found

## Tips

1. **Start simple:** Implement GET all tasks first, then add the others
2. **Use Quarkus dev mode:** Changes are reflected immediately
3. **Check the tests:** They show what's expected
4. **Look at TODO comments:** They guide you through the implementation
5. **Use the solution:** If stuck, peek at the solution folder

## Common Issues

**Issue:** Tests fail with "Connection refused"
- **Solution:** Make sure you're running `mvn test`, not trying to connect to a running instance

**Issue:** JSON serialization errors
- **Solution:** Ensure your Task class has getters and setters

**Issue:** 404 on all endpoints
- **Solution:** Check that your `@Path` annotations are correct

## Verification

Run the tests to verify your solution:
```bash
mvn test
```

Expected output:
```
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
```

## Next Steps

Once you've completed this exercise:
1. Explore the solution in the `solution/` folder
2. Try adding validation (e.g., title cannot be empty)
3. Move on to [Exercise 2](../exercise-2/README.md) to add database persistence!

## Additional Challenges (Optional)

If you finish early, try these enhancements:
1. Add validation to ensure `title` is not null or empty
2. Add a PATCH endpoint to toggle the `completed` status
3. Add filtering: GET /api/tasks?completed=true
4. Add sorting: GET /api/tasks?sort=title

Great job! You've created your first Quarkus REST API! 🎉
