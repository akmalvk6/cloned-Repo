package com.example.BHRC.dto;

public class GenericResponse {

    private Boolean status;
    private String message;
    private Object data; // Use Object here to make it flexible for different models
    private String error;

    // Default constructor
    public GenericResponse() {}

    // Constructor with status and message
    public GenericResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    // Constructor with all fields
    public GenericResponse(Boolean status, String message, Object data, String error) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    // Getters and setters
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

