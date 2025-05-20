package org.stibo;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.stibo.entity.User;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class UserResourceTest {


    @Test
    @TestTransaction
    public void testCreateUserEndpoint() {
        String userJson = "{\"username\": \"testuser\", \"firstName\": \"Test\", \"lastName\": \"User\", \"email\": \"test@example.com\"}";

        given()
                .contentType(ContentType.JSON)
                .body(userJson)
                .when().post("/user")
                .then()
                .statusCode(201)
                .header("Location", CoreMatchers.containsString("/user/"))
                .header("Location", CoreMatchers.not(CoreMatchers.containsString("/user/0")));
    }

    @Test
    @TestTransaction
    public void testCreateUserEndpoint_ValidationFailure() {
        String invalidUserJson = "{\"username\": \"\", \"firstName\": \"\", \"lastName\": \"\", \"email\": \"invalid-email\"}";

        given()
                .contentType(ContentType.JSON)
                .body(invalidUserJson)
                .when().post("/user")
                .then()
                .statusCode(400); // Expecting a Bad Request due to validation errors
    }

    @Test
    @TestTransaction
    public void testGetUserEndpoint() {
        String userJson = "{\"username\": \"testuser\", \"firstName\": \"Test\", \"lastName\": \"User\", \"email\": \"test_get@example.com\"}";

        // Create user and extract Location header
        String location = given()
        .contentType(ContentType.JSON)
        .body(userJson)
        .when().post("/user")
        .then()
        .statusCode(201)
        .extract()
        .header("Location");

        // Extract user ID from Location header (e.g., "/user/5")
        String id = location.substring(location.lastIndexOf('/') + 1);

        // Use extracted ID in the get call
        given()
                .when().get("/user/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(User.class);
    }

    @Test
    @TestTransaction
    public void testGetUserEndpoint_NotFound() {
        given()
                .when().get("/user/999") // Assuming user with ID 999 does not exist
                .then()
                .statusCode(404);
    }

    @Test
    @TestTransaction
    public void testDeleteUserEndpoint() {
        String userJson = "{\"username\": \"testuser\", \"firstName\": \"Test\", \"lastName\": \"User\", \"email\": \"test_delete@example.com\"}";

        // Create user and extract Location header
        String location = given()
        .contentType(ContentType.JSON)
        .body(userJson)
        .when().post("/user")
        .then()
        .statusCode(201)
        .extract()
        .header("Location");

        // Extract user ID from Location header (e.g., "/user/5")
        String id = location.substring(location.lastIndexOf('/') + 1);

        // Use extracted ID in the delete call
        given()
                .when().delete("/user/" + id) // Assuming user with ID 1 exists
                .then()
                .statusCode(200)
                .body(CoreMatchers.is("true"));
    }

    @Test
    @TestTransaction
    public void testDeleteUserEndpoint_NotFound() {
        given()
                .when().delete("/user/999") // Assuming user with ID 999 does not exist
                .then()
                .statusCode(200) // Or 404, depending on your implementation
                .body(CoreMatchers.is("false"));
    }
}