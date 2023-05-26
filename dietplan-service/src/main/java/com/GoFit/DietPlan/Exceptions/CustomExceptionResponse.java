package com.GoFit.DietPlan.Exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class CustomExceptionResponse {

    private HttpStatus status;
    //General error message about nature of error
    private String message;
    //Specific errors in API request processing
    private Map<String, String> errors;


    public CustomExceptionResponse() {
    }

    public CustomExceptionResponse(HttpStatus status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
