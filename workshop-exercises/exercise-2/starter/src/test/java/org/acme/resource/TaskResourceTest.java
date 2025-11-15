package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * Tests for TaskResource with database integration
 *
 * These tests verify that your database persistence is working correctly.
 * Run with: mvn test
 */
@QuarkusTest
public class TaskResourceTest {

    @Test
    public void testGetAllTasksEndpoint() {
        given()
            .when().get("/api/tasks")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("$", notNullValue());
    }

    @Test
    public void testCreateTask() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Test Task\",\"description\":\"Test Description\",\"completed\":false}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("title", equalTo("Test Task"))
            .body("description", equalTo("Test Description"))
            .body("completed", equalTo(false))
            .body("createdAt", notNullValue());
    }

    @Test
    public void testGetTaskById() {
        // First create a task
        int taskId = given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Task to Get\",\"description\":\"Description\",\"completed\":false}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201)
            .extract().path("id");

        // Then get it by ID
        given()
            .when().get("/api/tasks/" + taskId)
            .then()
            .statusCode(200)
            .body("id", equalTo(taskId))
            .body("title", equalTo("Task to Get"))
            .body("createdAt", notNullValue());
    }

    @Test
    public void testGetNonExistentTask() {
        given()
            .when().get("/api/tasks/99999")
            .then()
            .statusCode(404);
    }

    @Test
    public void testUpdateTask() {
        // First create a task
        int taskId = given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Original Title\",\"description\":\"Original Description\",\"completed\":false}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201)
            .extract().path("id");

        // Then update it
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Updated Title\",\"description\":\"Updated Description\",\"completed\":true}")
            .when().put("/api/tasks/" + taskId)
            .then()
            .statusCode(200)
            .body("title", equalTo("Updated Title"))
            .body("description", equalTo("Updated Description"))
            .body("completed", equalTo(true));
    }

    @Test
    public void testUpdateNonExistentTask() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Updated Title\",\"description\":\"Updated Description\",\"completed\":true}")
            .when().put("/api/tasks/99999")
            .then()
            .statusCode(404);
    }

    @Test
    public void testDeleteTask() {
        // First create a task
        int taskId = given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Task to Delete\",\"description\":\"Description\",\"completed\":false}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201)
            .extract().path("id");

        // Then delete it
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

    @Test
    public void testDeleteNonExistentTask() {
        given()
            .when().delete("/api/tasks/99999")
            .then()
            .statusCode(404);
    }

    @Test
    public void testFilterByCompletedStatus() {
        // Create a completed task
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Completed Task\",\"description\":\"Done\",\"completed\":true}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201);

        // Create an incomplete task
        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Incomplete Task\",\"description\":\"Not done\",\"completed\":false}")
            .when().post("/api/tasks")
            .then()
            .statusCode(201);

        // Filter by completed=true
        given()
            .when().get("/api/tasks/completed/true")
            .then()
            .statusCode(200)
            .body("$", notNullValue())
            .body("findAll { it.completed == true }.size()", greaterThan(0));

        // Filter by completed=false
        given()
            .when().get("/api/tasks/completed/false")
            .then()
            .statusCode(200)
            .body("$", notNullValue())
            .body("findAll { it.completed == false }.size()", greaterThan(0));
    }
}
