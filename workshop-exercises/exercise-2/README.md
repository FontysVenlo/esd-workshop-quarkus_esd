# Exercise 2: Database Integration with Hibernate Panache

**Duration:** 30 minutes

**Difficulty:** Intermediate

## Learning Objectives

By completing this exercise, you will learn:
- How to use Hibernate ORM with Panache in Quarkus
- Creating JPA entities with Panache's Active Record pattern
- Database configuration with H2 (in-memory)
- Transaction management with `@Transactional`
- Testing with database integration
- Database initialization with import.sql

## Overview

In this exercise, you'll enhance the Task Management API by adding database persistence using Hibernate ORM with Panache. Instead of storing tasks in memory, they will be persisted to an H2 database.

**Panache** is a Quarkus-specific library that simplifies Hibernate ORM by reducing boilerplate code. It provides two patterns:
- **Active Record Pattern:** Entities have built-in persistence methods (we'll use this!)
- **Repository Pattern:** Separate repository classes

## Task Entity

The Task entity will have:
- `id` (Long): Primary key, auto-generated
- `title` (String): Task title (required)
- `description` (String): Detailed description
- `completed` (Boolean): Completion status (default: false)
- `createdAt` (LocalDateTime): Creation timestamp

## API Endpoints to Implement

| Method | Path | Description | Request Body | Response |
|--------|------|-------------|--------------|----------|
| GET | /api/tasks | Get all tasks | - | List of tasks |
| GET | /api/tasks/{id} | Get task by ID | - | Single task or 404 |
| POST | /api/tasks | Create new task | Task (without ID) | Created task with ID |
| PUT | /api/tasks/{id} | Update existing task | Task | Updated task or 404 |
| DELETE | /api/tasks/{id} | Delete task | - | 204 No Content or 404 |
| GET | /api/tasks/completed/{status} | Filter by completion | - | List of tasks |

## Project Structure

```
starter/
├── src/
│   ├── main/
│   │   ├── java/org/acme/
│   │   │   ├── entity/
│   │   │   │   └── Task.java          # TODO: Create Panache entity
│   │   │   └── resource/
│   │   │       └── TaskResource.java   # TODO: Implement REST endpoints with DB
│   │   └── resources/
│   │       ├── application.properties  # TODO: Configure database
│   │       └── import.sql              # TODO: Add sample data
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
   cd workshop-exercises/exercise-2/starter
   ```

2. Start Quarkus in dev mode:
   ```bash
   mvn quarkus:dev
   ```

### Step 2: Create the Panache Entity

Open `src/main/java/org/acme/entity/Task.java` and complete the TODOs:

1. Make the class extend `PanacheEntity` (this gives you `id` for free!)
2. Add the `@Entity` annotation
3. Add fields: `title`, `description`, `completed`, `createdAt`
4. Add column annotations where appropriate
5. Use `@PrePersist` to set `createdAt` timestamp

**Key Panache Features:**
```java
// PanacheEntity gives you:
// - public Long id (auto-generated)
// - persist() method
// - delete() method
// - Static query methods: findAll(), findById(), etc.
```

**Example Panache methods you'll use:**
```java
Task.listAll()              // Get all tasks
Task.findById(id)           // Find by ID
task.persist()              // Save/update task
task.delete()               // Delete task
Task.find("completed", true).list()  // Query by field
```

### Step 3: Configure the Database

Open `src/main/resources/application.properties` and complete the TODOs:

1. Configure H2 database connection
2. Set Hibernate to create tables automatically in dev mode
3. Enable SQL logging (helpful for debugging)
4. Configure database initialization

### Step 4: Add Sample Data

Open `src/main/resources/import.sql` and add some sample INSERT statements:

```sql
INSERT INTO Task(id, title, description, completed, createdAt) VALUES (1, 'Sample Task', 'Description', false, CURRENT_TIMESTAMP);
```

This file is automatically executed when the application starts in dev mode.

### Step 5: Implement REST Endpoints with Database

Open `src/main/java/org/acme/resource/TaskResource.java` and implement:

1. **GET /api/tasks** - Return all tasks from database
   - Use `Task.listAll()`
   - No transaction needed for reads

2. **GET /api/tasks/{id}** - Get task by ID
   - Use `Task.findById(id)`
   - Return 404 if not found

3. **POST /api/tasks** - Create a new task
   - Add `@Transactional` annotation
   - Set `createdAt` and `completed` if not set
   - Use `task.persist()`
   - Return HTTP 201

4. **PUT /api/tasks/{id}** - Update a task
   - Add `@Transactional` annotation
   - Find existing task
   - Update fields
   - Automatically saved due to transaction
   - Return 404 if not found

5. **DELETE /api/tasks/{id}** - Delete a task
   - Add `@Transactional` annotation
   - Find task and call `task.delete()`
   - Return 204 or 404

6. **GET /api/tasks/completed/{status}** - Filter by completion status
   - Use `Task.find("completed", status).list()`

**Important:** Write operations (POST, PUT, DELETE) must be annotated with `@Transactional`!

### Step 6: Test Your Implementation

1. **Run the tests:**
   ```bash
   mvn test
   ```

2. **Manual testing:**

   Create a task:
   ```bash
   curl -X POST http://localhost:8080/api/tasks \
     -H "Content-Type: application/json" \
     -d '{"title":"Buy groceries","description":"Milk, eggs, bread","completed":false}'
   ```

   Get all tasks:
   ```bash
   curl http://localhost:8080/api/tasks
   ```

   Filter completed tasks:
   ```bash
   curl http://localhost:8080/api/tasks/completed/true
   ```

3. **Check the H2 Console:**
   - Visit http://localhost:8080/q/dev
   - Click on "Datasources" to see your database
   - You can execute SQL queries directly!

### Step 7: Explore Panache Features

Try these Panache query methods in your code:

```java
// Count tasks
long count = Task.count();

// Find by field
List<Task> completedTasks = Task.find("completed", true).list();

// Delete all completed tasks
Task.delete("completed", true);

// Custom query
List<Task> tasks = Task.find("ORDER BY createdAt DESC").list();

// Pagination
List<Task> page = Task.findAll().page(0, 10).list();
```

## Expected Behavior

- All data is persisted to the H2 database
- Restarting the app loads data from import.sql
- Tests run with a separate test database (isolated)
- Transactions ensure data consistency

## Database Schema

The Task table will look like:

```sql
CREATE TABLE Task (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    completed BOOLEAN DEFAULT FALSE,
    createdAt TIMESTAMP
);
```

## Tips

1. **Transactions are essential:** Forget `@Transactional` and your changes won't be saved!
2. **Panache simplifies queries:** No need for repositories or complex JPQL
3. **Use Dev UI:** The datasource viewer is very helpful
4. **Check import.sql:** Make sure your SQL syntax is correct
5. **Look at the solution:** If stuck, compare with the solution folder

## Common Issues

**Issue:** Changes not persisted to database
- **Solution:** Add `@Transactional` to write operations

**Issue:** NullPointerException on findById
- **Solution:** Check for null: `Task task = Task.findById(id); if (task == null) return 404;`

**Issue:** Table not created
- **Solution:** Check application.properties - ensure `quarkus.hibernate-orm.database.generation=drop-and-create` in dev mode

**Issue:** import.sql not executed
- **Solution:** Check SQL syntax, ensure file is in src/main/resources/

## Verification

Run the tests to verify your solution:
```bash
mvn test
```

Expected output:
```
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
```

## Next Steps

Once you've completed this exercise:
1. Explore the solution in the `solution/` folder
2. Try adding custom queries with Panache
3. Experiment with different database queries in the H2 console
4. Move on to [Exercise 3](../exercise-3/README.md) for advanced features!

## Additional Challenges (Optional)

If you finish early, try:
1. Add validation annotations (`@NotNull`, `@Size`) to Task fields
2. Add a `dueDate` field and query tasks due soon
3. Implement pagination: `GET /api/tasks?page=0&size=10`
4. Add a search endpoint: `GET /api/tasks/search?title=...`
5. Create a custom Panache query method

Congratulations! You've mastered Hibernate ORM with Panache! 🎉
