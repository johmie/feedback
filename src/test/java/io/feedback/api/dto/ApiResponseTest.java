package io.feedback.api.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiResponseTest {

    @Test
    public void constructor_SetsTimestamp() {
        LocalDateTime before = LocalDateTime.now();
        ApiResponse<String> response = new ApiResponse<>();
        LocalDateTime after = LocalDateTime.now();

        assertNotNull(response.getTimestamp());
        assertTrue(response.getTimestamp().isAfter(before) || response.getTimestamp().isEqual(before));
        assertTrue(response.getTimestamp().isBefore(after) || response.getTimestamp().isEqual(after));
    }

    @Test
    public void constructor_WithParams_SetsAllFields() {
        ApiResponse<String> response = new ApiResponse<>(true, "message", "data");

        assertTrue(response.isSuccess());
        assertEquals("message", response.getMessage());
        assertEquals("data", response.getData());
    }

    @Test
    public void success_WithData_ReturnsSuccessResponse() {
        String data = "testData";

        ApiResponse<String> response = ApiResponse.success(data);

        assertTrue(response.isSuccess());
        assertEquals("Success", response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    public void success_WithMessageAndData_ReturnsSuccessResponse() {
        String message = "Custom message";
        String data = "testData";

        ApiResponse<String> response = ApiResponse.success(message, data);

        assertTrue(response.isSuccess());
        assertEquals(message, response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    public void error_WithMessage_ReturnsErrorResponse() {
        String message = "Error message";

        ApiResponse<String> response = ApiResponse.error(message);

        assertFalse(response.isSuccess());
        assertEquals(message, response.getMessage());
        assertNull(response.getData());
    }

    @Test
    public void error_WithMessageAndErrors_ReturnsErrorResponseWithErrors() {
        String message = "Error message";
        List<String> errors = Arrays.asList("error1", "error2");

        ApiResponse<String> response = ApiResponse.error(message, errors);

        assertFalse(response.isSuccess());
        assertEquals(message, response.getMessage());
        assertNull(response.getData());
        assertEquals(errors, response.getErrors());
    }

    @Test
    public void setters_SetValuesCorrectly() {
        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("message");
        response.setData("data");
        List<String> errors = Arrays.asList("error1");
        response.setErrors(errors);
        LocalDateTime timestamp = LocalDateTime.now();
        response.setTimestamp(timestamp);

        assertTrue(response.isSuccess());
        assertEquals("message", response.getMessage());
        assertEquals("data", response.getData());
        assertEquals(errors, response.getErrors());
        assertEquals(timestamp, response.getTimestamp());
    }
}