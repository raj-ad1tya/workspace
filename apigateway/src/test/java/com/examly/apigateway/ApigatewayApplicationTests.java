package com.examly.apigateway;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApigatewayApplicationTests {

    private String usertoken;
    private String admintoken;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper; // To parse JSON responses

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    @Order(1)
    void backend_testRegisterAdmin() {
        String requestBody = "{\"userId\": 1,\"email\": \"demoadmin@gmail.com\", \"password\": \"admin@1234\", \"username\": \"admin123\", \"userRole\": \"ADMIN\", \"mobileNumber\": \"9876543210\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(2)
    void backend_testRegisterUser() {
        String requestBody = "{\"userId\": 2,\"email\": \"demouser@gmail.com\", \"password\": \"user@1234\", \"username\": \"user123\", \"userRole\": \"USER\", \"mobileNumber\": \"1122334455\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(3)
    void backend_testLoginAdmin() throws Exception {
        String requestBody = "{\"email\": \"demoadmin@gmail.com\", \"password\": \"admin@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

    // Check if response body is null
    Assertions.assertNotNull(response.getBody(), "Response body is null!");

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        admintoken = token;

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }

    @Test
    @Order(4)
    void backend_testLoginUser() throws Exception {
        String requestBody = "{\"email\": \"demouser@gmail.com\", \"password\": \"user@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        usertoken = token;

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }



    @Test
    @Order(5)
    void backend_testAddBusWithRoleValidation() throws Exception {
        // Ensure tokens are available
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");
    
        String requestBody = "{"
            + "\"busName\": \"Express Bus\","
            + "\"source\": \"New York\","
            + "\"destination\": \"Washington D.C.\","
            + "\"departureTime\": \"2025-04-01T08:00\","
            + "\"arrivalTime\": \"2025-04-01T12:00\","
            + "\"totalSeats\": 50,"
            + "\"availableSeats\": 50,"
            + "\"busType\": \"AC\","
            + "\"fare\": 25.5,"
            + "\"photo\": \"image_base64_data\","
            + "\"user\": {\"userId\": 1}"
            + "}";
    
        // **✅ Test with Admin token (Expecting 201 Created)**
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);
    
        ResponseEntity<String> adminResponse = restTemplate.exchange("/api/bus", HttpMethod.POST, adminRequest, String.class);
        
        // Debugging logs
        System.out.println("Response Status (Admin): " + adminResponse.getStatusCode());
        System.out.println("Response Body (Admin): " + adminResponse.getBody());
    
        Assertions.assertEquals(HttpStatus.CREATED, adminResponse.getStatusCode());
        
        JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());
        Assertions.assertEquals("Express Bus", responseBody.get("busName").asText());
        Assertions.assertEquals("New York", responseBody.get("source").asText());
        Assertions.assertEquals("Washington D.C.", responseBody.get("destination").asText());
        Assertions.assertEquals(50, responseBody.get("totalSeats").asInt());
        Assertions.assertEquals(25.5, responseBody.get("fare").asDouble());
    
        // **❌ Test with User token (Expecting 403 Forbidden)**
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);
    
        ResponseEntity<String> userResponse = restTemplate.exchange("/api/bus", HttpMethod.POST, userRequest, String.class);
        
        // Debugging logs
        System.out.println("Response Status (User): " + userResponse.getStatusCode());
        System.out.println("Response Body (User): " + userResponse.getBody());
    
        Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode()); // Ensure user cannot add a bus
    }
    




    @Test
    @Order(6)
    void backend_testGetBusByIdWithRoleValidation() throws Exception {
        // Ensure tokens are available
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");
    
        int busId = 1;
        String url = "/api/bus/" + busId;
    
        // Test with Admin token (Expecting 200 OK)
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<Void> adminRequest = new HttpEntity<>(adminHeaders);
    
        ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.GET, adminRequest, String.class);
    
        System.out.println(adminResponse.getStatusCode() + " Status code for Admin retrieving food");
        Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());
    
        // Parse and validate JSON response
        JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());
    
        Assertions.assertEquals(1, responseBody.get("busId").asInt());
        Assertions.assertEquals("Express Bus", responseBody.get("busName").asText());
    
        System.out.println("Admin Response JSON: " + responseBody.toString());
    
        // Test with User token (Expecting 200 OK)
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);
    
        ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);
    
        System.out.println(userResponse.getStatusCode() + " Status code for User retrieving food");
        Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
    }
    
    



    @Test
    @Order(7)
    void backend_testGetAllBuses() throws Exception {
        // Ensure tokens are available
        Assertions.assertNotNull(admintoken, "Admin token should not be null");
        Assertions.assertNotNull(usertoken, "User token should not be null");
    
        // Test GET with Admin token (Expecting 200 OK)
        HttpHeaders adminHeaders = createHeaders();
        adminHeaders.set("Authorization", "Bearer " + admintoken);
        HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);
    
        ResponseEntity<String> adminResponse = restTemplate.exchange("/api/bus", HttpMethod.GET, adminRequest, String.class);
        JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());
    
        System.out.println(adminResponse.getStatusCode() + " Status code for Admin getting all buses");
        Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());
        Assertions.assertTrue(responseBody.isArray());
    
        // Test GET with User token (Expecting 200 OK)
        HttpHeaders userHeaders = createHeaders();
        userHeaders.set("Authorization", "Bearer " + usertoken);
        HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);
    
        ResponseEntity<String> userResponse = restTemplate.exchange("/api/bus", HttpMethod.GET, userRequest, String.class);
        JsonNode userResponseBody = objectMapper.readTree(userResponse.getBody());
    
        System.out.println(userResponse.getStatusCode() + " Status code for User getting all buses");
        Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
        Assertions.assertTrue(userResponseBody.isArray());
    }




@Test
@Order(8)
void backend_testUpdateBusWithRoleValidation() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    int busId = 1;  // Assuming bus with ID 1 exists
    String url = "/api/bus/" + busId;

    String updateRequestBody = "{"
        + "\"busId\": " + busId + ","
        + "\"busName\": \"Updated Express Bus\","
        + "\"source\": \"Los Angeles\","
        + "\"destination\": \"San Francisco\","
        + "\"departureTime\": \"2025-04-05T10:00\","
        + "\"arrivalTime\": \"2025-04-05T15:00\","
        + "\"totalSeats\": 45,"
        + "\"availableSeats\": 40,"
        + "\"busType\": \"Sleeper\","
        + "\"fare\": 35.0,"
        + "\"photo\": \"updatedBase64String\","
        + "\"user\": {\"userId\": 1}"
        + "}";

    // **✅ Test with Admin token (Expecting 200 OK)**
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(updateRequestBody, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.PUT, adminRequest, String.class);

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin updating bus");
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

    JsonNode responseBody = objectMapper.readTree(adminResponse.getBody());

    Assertions.assertEquals(busId, responseBody.get("busId").asLong());
    Assertions.assertEquals("Updated Express Bus", responseBody.get("busName").asText());
    Assertions.assertEquals("Los Angeles", responseBody.get("source").asText());
    Assertions.assertEquals("San Francisco", responseBody.get("destination").asText());
    Assertions.assertEquals(45, responseBody.get("totalSeats").asInt());
   // Assertions.assertEquals(35.0, responseBody.get("fare").asDouble());

    System.out.println("Admin Update Response JSON: " + responseBody.toString());

    // **❌ Test with User token (Expecting 403 Forbidden)**
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(updateRequestBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.PUT, userRequest, String.class);

    System.out.println(userResponse.getStatusCode() + " Status code for User trying to update bus");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}




@Test
@Order(9)
void backend_testUserCanAddBooking() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(usertoken, "User token should not be null");
    Assertions.assertNotNull(admintoken, "Admin token should not be null");

    String requestBody = "{"
        + "\"bookingDate\": \"2025-04-10\","
        + "\"numberOfSeats\": 2,"
        + "\"seatNumbers\": [5, 6],"
        + "\"status\": \"CONFIRMED\","
        + "\"user\": {\"userId\": 2},"
        + "\"bus\": {\"busId\": 1}"
        + "}";

    // **✅ Test with User token (Expecting 201 Created)**
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange("/api/bookings", HttpMethod.POST, userRequest, String.class);
    JsonNode responseBody = objectMapper.readTree(userResponse.getBody());

    System.out.println(userResponse.getStatusCode() + " Status code for User adding booking");
    Assertions.assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());
    Assertions.assertEquals(2, responseBody.get("numberOfSeats").asInt());
    Assertions.assertEquals("CONFIRMED", responseBody.get("status").asText());

    // **❌ Test with Admin token (Expecting 403 Forbidden)**
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange("/api/bookings", HttpMethod.POST, adminRequest, String.class);

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin trying to add a booking");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
}


    

@Test
@Order(10)
void backend_testAdminCanUpdateBooking() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(usertoken, "User token should not be null");
    Assertions.assertNotNull(admintoken, "Admin token should not be null");

    int bookingId = 1;
    String url = "/api/bookings/" + bookingId;

        String adminUpdateRequestBody = "{"
        + "\"bookingId\": " + bookingId + ","
        + "\"bookingDate\": \"2025-04-15\","
        + "\"numberOfSeats\": " + 3 + ","
        + "\"seatNumbers\": [7, 8, 9],"
        + "\"status\": \"CONFIRMED\","
        + "\"user\": {\"userId\": 1}" // Correctly formatted user object
        + "}";

    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(adminUpdateRequestBody, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.PUT, adminRequest, String.class);
    JsonNode adminResponseBody = objectMapper.readTree(adminResponse.getBody());

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin updating booking");
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());
    Assertions.assertEquals(3, adminResponseBody.get("numberOfSeats").asInt());
    Assertions.assertEquals("CONFIRMED", adminResponseBody.get("status").asText());

    System.out.println("Admin Update Response JSON: " + adminResponseBody.toString());

    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(adminUpdateRequestBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.PUT, userRequest, String.class);

    System.out.println(userResponse.getStatusCode() + " Status code for User trying to update bus");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}


@Test
@Order(11)
void backend_testRoleBasedAccessForViewingAllBookings() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    String url = "/api/bookings";

    // ✅ Test with Admin token (Expecting 200 OK)
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<Void> adminRequest = new HttpEntity<>(adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.GET, adminRequest, String.class);
    JsonNode adminResponseBody = objectMapper.readTree(adminResponse.getBody());

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin viewing all bookings");
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());
    Assertions.assertTrue(adminResponseBody.isArray());

    System.out.println("Admin View Bookings Response JSON: " + adminResponseBody.toString());

    // ❌ Test with User token (Expecting 403 Forbidden)
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);

    System.out.println(userResponse.getStatusCode() + " Status code for User trying to view all bookings");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}


@Test
@Order(12)
void backend_testUserCanViewOwnBookings() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(usertoken, "User token should not be null");

    int userId = 2; // Assuming user ID 1 is the logged-in user
    String url = "/api/bookings/user/" + userId; // Adjust API path if needed

    // ✅ Test with User token (Expecting 200 OK)
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);
    JsonNode userResponseBody = objectMapper.readTree(userResponse.getBody());

    System.out.println(userResponse.getStatusCode() + " Status code for User viewing their own bookings");
    Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
    Assertions.assertTrue(userResponseBody.isArray());

    System.out.println("User View Own Bookings Response JSON: " + userResponseBody.toString());

    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.GET, adminRequest, String.class);

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin trying to add a booking");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());

    // ❌ Test with another user's token (Expecting 403 Forbidden)
}


    

@Test
@Order(13)
void backend_testAddFeedbackWithRoleValidation() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    String requestBody = "{"
    + "\"feedbackId\": 1,"
    + "\"comments\": \"Great service!\","  // Changed "message" to "feedbackText"
    + "\"rating\": 5,"
    + "\"user\": { \"userId\": 2 },"
    + "\"bus\": { \"busId\": 1 },"
    + "\"date\": \"2025-02-27\""  // Added date field to match your model
    + "}";


    // Test with User token (Expecting 201 Created)
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange("/api/feedback", HttpMethod.POST, userRequest, String.class);
    JsonNode responseBody = objectMapper.readTree(userResponse.getBody());

    System.out.println(userResponse.getStatusCode() + " Status code for User adding feedback");
    Assertions.assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());
    Assertions.assertEquals("Great service!", responseBody.get("comments").asText());
    Assertions.assertEquals(5, responseBody.get("rating").asInt());


    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange("/api/feedback", HttpMethod.POST, adminRequest, String.class);

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin trying to add a Feedback");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
}

@Test
@Order(14)
void backend_testGetAllFeedbackWithRoleValidation() throws Exception {
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    // Admin should be able to view all feedback
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange("/api/feedback", HttpMethod.GET, adminRequest, String.class);
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

    // User should NOT be able to view all feedback
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange("/api/feedback", HttpMethod.GET, userRequest, String.class);
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}

@Test
@Order(15)
void backend_testGetFeedbackByUserIdWithRoleValidation() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    String url = "/api/feedback/user/2";

    // User should be able to view their own feedback
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);
    Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());

    // Admin should NOT be able to view feedback by user ID (Expecting 403 Forbidden)
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.GET, adminRequest, String.class);
    System.out.println(adminResponse.getStatusCode() + " Status code for Admin trying to get user feedback");

    // Admin should receive 403 Forbidden since admins should not be able to view this feedback
    Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
}

}
