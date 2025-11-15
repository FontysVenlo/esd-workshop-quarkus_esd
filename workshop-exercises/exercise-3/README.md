# Exercise 3: Configuration & Advanced Features

**Duration:** 20 minutes

**Difficulty:** Intermediate

## Learning Objectives

By completing this exercise, you will learn:
- Configuration management with @ConfigProperty
- Creating business logic services with CDI
- Adding custom health checks
- Auto-generating OpenAPI/Swagger documentation
- Adding metadata and tags to REST endpoints
- Injecting configuration into services

## Overview

In this exercise, you'll enhance the Task Management API with configuration-driven features, business logic services, health monitoring, and API documentation.

## New Features to Implement

1. **Task Priority System** - Add priority levels (LOW, MEDIUM, HIGH) with configuration
2. **Task Statistics Service** - Calculate task metrics (completion rate, counts)
3. **Custom Health Checks** - Monitor database connectivity and task counts
4. **OpenAPI Documentation** - Auto-generated Swagger UI with descriptions

## Task Entity Enhancement

The Task entity will be enhanced with:
- `priority` (String): Priority level (LOW, MEDIUM, HIGH)
- Validation based on configured allowed priorities

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | /api/tasks | Get all tasks (with OpenAPI docs) |
| GET | /api/tasks/{id} | Get task by ID |
| POST | /api/tasks | Create new task with priority |
| PUT | /api/tasks/{id} | Update existing task |
| DELETE | /api/tasks/{id} | Delete task |
| GET | /api/tasks/priority/{priority} | Filter by priority |
| GET | /api/tasks/stats | Get task statistics |
| GET | /q/health | Health check endpoint |
| GET | /q/swagger-ui | Swagger UI (auto-generated) |

## Project Structure

```
starter/
├── src/
│   ├── main/
│   │   ├── java/org/acme/
│   │   │   ├── entity/
│   │   │   │   └── Task.java          # TODO: Add priority field
│   │   │   ├── service/
│   │   │   │   ├── TaskService.java   # TODO: Create business logic service
│   │   │   │   └── TaskHealthCheck.java # TODO: Create custom health check
│   │   │   └── resource/
│   │   │       └── TaskResource.java   # TODO: Add OpenAPI annotations
│   │   └── resources/
│   │       ├── application.properties  # TODO: Add configuration
│   │       └── import.sql
│   └── test/
│       └── java/org/acme/
│           └── resource/
│               └── TaskResourceTest.java
└── pom.xml
```

## Instructions

### Step 1: Set Up the Project

1. Navigate to the starter directory:
   ```bash
   cd workshop-exercises/exercise-3/starter
   ```

2. Start Quarkus in dev mode:
   ```bash
   mvn quarkus:dev
   ```

### Step 2: Add Configuration

Open `src/main/resources/application.properties` and add:

1. **Task Priority Configuration:**
   ```properties
   # Task priorities configuration
   task.priority.default=MEDIUM
   task.priority.allowed=LOW,MEDIUM,HIGH
   task.max.description.length=500
   ```

2. **Health Check Configuration:**
   ```properties
   # Health check configuration
   task.health.max.count=1000
   ```

### Step 3: Enhance Task Entity with Priority

Open `src/main/java/org/acme/entity/Task.java`:

1. Add a `priority` field (String)
2. Set default priority in `@PrePersist` method
3. Add getter and setter

### Step 4: Create TaskService with Configuration

Open `src/main/java/org/acme/service/TaskService.java`:

This service will:
- Inject configuration values using `@ConfigProperty`
- Provide business logic methods
- Calculate task statistics

**Key concepts:**
```java
@ApplicationScoped  // Makes it a CDI bean
public class TaskService {

    @ConfigProperty(name = "task.priority.default")
    String defaultPriority;

    // Business logic methods
}
```

TODO:
1. Add `@ApplicationScoped` annotation
2. Inject `task.priority.default` configuration
3. Implement `getTaskStatistics()` method
4. Implement `validatePriority()` method

### Step 5: Create Custom Health Check

Open `src/main/java/org/acme/service/TaskHealthCheck.java`:

Create a health check that verifies:
- Database is accessible
- Task count is below max threshold

**Key concepts:**
```java
@Health  // Registers as a health check
public class TaskHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        // Check health and return status
    }
}
```

TODO:
1. Implement `HealthCheck` interface
2. Add `@Health` annotation
3. Check if task count is below threshold
4. Return UP or DOWN status with details

### Step 6: Add OpenAPI Documentation

Open `src/main/java/org/acme/resource/TaskResource.java`:

Add OpenAPI annotations:
- `@Tag` on the class
- `@Operation` on each method
- `@APIResponse` for different status codes

**Example:**
```java
@Tag(name = "Task Management", description = "Operations for managing tasks")
@Path("/api/tasks")
public class TaskResource {

    @Operation(summary = "Get all tasks", description = "Returns all tasks from the database")
    @APIResponse(responseCode = "200", description = "Success")
    @GET
    public List<Task> getAllTasks() { ... }
}
```

TODO:
1. Add `@Tag` to the class
2. Add `@Operation` to each method
3. Add `@APIResponse` annotations
4. Add descriptions explaining what each endpoint does

### Step 7: Implement Statistics Endpoint

Add a new endpoint in `TaskResource.java`:

```java
@GET
@Path("/stats")
public Response getStatistics() {
    // Use TaskService to calculate stats
}
```

This should return:
```json
{
  "total": 10,
  "completed": 5,
  "pending": 5,
  "completionRate": 50.0,
  "byPriority": {
    "HIGH": 3,
    "MEDIUM": 5,
    "LOW": 2
  }
}
```

### Step 8: Test Your Implementation

1. **Run tests:**
   ```bash
   mvn test
   ```

2. **Check Swagger UI:**
   - Visit http://localhost:8080/q/swagger-ui
   - You should see all your endpoints with documentation
   - Try executing requests directly from the UI!

3. **Check Health:**
   ```bash
   curl http://localhost:8080/q/health
   ```

   Expected response:
   ```json
   {
     "status": "UP",
     "checks": [
       {
         "name": "Task Database Health",
         "status": "UP",
         "data": {
           "taskCount": 5,
           "maxAllowed": 1000
         }
       }
     ]
   }
   ```

4. **Test Configuration:**
   ```bash
   # Create task without priority (should use default)
   curl -X POST http://localhost:8080/api/tasks \
     -H "Content-Type: application/json" \
     -d '{"title":"Test Task","description":"Testing"}'

   # Check it has MEDIUM priority (default)
   ```

5. **Test Statistics:**
   ```bash
   curl http://localhost:8080/q/api/tasks/stats
   ```

### Step 9: Explore Dev UI Features

Visit http://localhost:8080/q/dev and explore:
- **Config Editor** - View and modify configuration
- **OpenAPI** - See your API schema
- **Health Checks** - Monitor health status
- **Datasources** - View database connections

## Expected Behavior

- Tasks created without priority get the default priority (MEDIUM)
- Health check shows UP when task count is below threshold
- Swagger UI displays all endpoints with descriptions
- Statistics endpoint returns accurate counts and rates
- Configuration can be injected into services

## Tips

1. **Configuration:** Use `@ConfigProperty` to inject config values
2. **CDI:** Use `@ApplicationScoped` for singleton services
3. **Health Checks:** Return detailed information in HealthCheckResponse
4. **OpenAPI:** More annotations = better documentation
5. **Testing:** Test configuration-driven behavior

## Common Issues

**Issue:** Configuration not injected (null value)
- **Solution:** Ensure property name in `@ConfigProperty` matches application.properties

**Issue:** Health check not showing up
- **Solution:** Add `@Health` annotation and implement `HealthCheck` interface

**Issue:** Swagger UI not showing endpoint descriptions
- **Solution:** Add `@Operation` and `@APIResponse` annotations

**Issue:** Service not found (injection fails)
- **Solution:** Add `@ApplicationScoped` to the service class

## Verification

Run the tests:
```bash
mvn test
```

Expected output:
```
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
```

## Additional Challenges (Optional)

If you finish early, try:
1. Add validation to reject invalid priorities
2. Add a configuration for max tasks per user
3. Create a custom metric to track API call counts
4. Add filtering: GET /api/tasks?priority=HIGH&completed=true
5. Implement task archiving after X days (configured)
6. Add email notification configuration (mock implementation)

## Key Quarkus Features Learned

- **Configuration:** Type-safe config injection with @ConfigProperty
- **CDI:** Dependency injection with @Inject and @ApplicationScoped
- **Health Checks:** Custom health monitoring
- **OpenAPI:** Auto-generated API documentation
- **Dev Mode:** Live configuration updates and Dev UI

## Next Steps

Congratulations! You've completed all exercises! 🎉

You've learned:
- Building REST APIs with Quarkus
- Database integration with Hibernate Panache
- Configuration management
- Health monitoring
- API documentation

**What's next?**
- Explore [Quarkus guides](https://quarkus.io/guides/)
- Try building native images
- Learn about reactive programming with Quarkus
- Deploy to Kubernetes/OpenShift

Great work! You're now ready to build production-ready Quarkus applications!
