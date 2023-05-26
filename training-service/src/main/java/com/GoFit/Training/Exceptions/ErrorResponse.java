package com.GoFit.Training.Exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

//Class for consistent error message structure
public class ErrorResponse {

    private HttpStatus status;
    //General error message about nature of error
    private String message;
    //Specific errors in API request processing
    private Map<String, String> errors;

    public ErrorResponse(String message, Map<String, String> error, HttpStatus httpStatus) {
        this.message = message;
        this.errors = error;
        this.status = httpStatus;
    }

    public ErrorResponse() {
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
