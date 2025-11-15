package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * Tests for TaskResource with configuration and advanced features
 */
@QuarkusTest
public class TaskResourceTest {

    @Test
    public void testGetAllTasksEndpoint() {
        given()
            .when().get("/api/tasks")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

    @Test
    public void testCreateTaskWithPriority() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"High Priority Task\",\"description\":\"Important task\",\"completed\":false,\"priority\":\"HIGH\"}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201)
            .body("priority", equalTo("HIGH"));
    }

    @Test
    public void testCreateTaskWithDefaultPriority() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Task without priority\",\"description\":\"Should get default priority\"}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201)
            .body("priority", notNullValue());
    }

    @Test
    public void testGetTasksByPriority() {
        // Create a high priority task
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"High Priority\",\"priority\":\"HIGH\"}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201);

        // Filter by HIGH priority
        given()
            .when().get("/api/tasks/priority/HIGH")
            .then()
            .statusCode(200)
            .body("$", notNullValue());
    }

    @Test
    public void testGetStatistics() {
        given()
            .when().get("/api/tasks/stats")
            .then()
            .statusCode(200)
            .body("total", notNullValue())
            .body("completed", notNullValue())
            .body("pending", notNullValue())
            .body("completionRate", notNullValue())
            .body("byPriority", notNullValue());
    }

    @Test
    public void testHealthCheck() {
        given()
            .when().get("/q/health")
            .then()
            .statusCode(200)
            .body("status", equalTo("UP"))
            .body("checks.find { it.name == 'Task Database Health' }.status", equalTo("UP"));
    }

    @Test
    public void testSwaggerUIAvailable() {
        given()
            .when().get("/q/swagger-ui")
            .then()
            .statusCode(200);
    }

    @Test
    public void testOpenAPIEndpoint() {
        given()
            .when().get("/q/openapi")
            .then()
            .statusCode(200)
            .contentType("application/yaml");
    }

    @Test
    public void testUpdateTaskPriority() {
        // Create a task
        int taskId = given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Task\",\"priority\":\"LOW\"}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201)
            .extract().path("id");

        // Update priority
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Task\",\"priority\":\"HIGH\"}")
            .when().put("/api/tasks/" + taskId)
            .then()
            .statusCode(200)
            .body("priority", equalTo("HIGH"));
    }

    @Test
    public void testDeleteTask() {
        // Create a task
        int taskId = given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Task to Delete\"}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201)
            .extract().path("id");

        // Delete it
        given()
            .when().delete("/api/tasks/" + taskId)
            .then()
            .statusCode(204);

        // Verify it's gone
        given()
            .when().get("/api/tasks/" + taskId)
            .then()
            .statusCode(404);
    }
}
