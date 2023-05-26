package com.GoFit.DietPlan.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    //When record is not found in the database
    @ExceptionHandler(NotFoundException.class)
    public static final ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", exception.getLocalizedMessage());
        CustomExceptionResponse errorResponse = new CustomExceptionResponse(HttpStatus.NOT_FOUND, "Record not found.", errorDetails);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    //When integrity constraint (foreign key, primary key or unique key) has been violated.
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();

        errorDetails.put("error", exception.getLocalizedMessage());
        CustomExceptionResponse errorResponse = new CustomExceptionResponse(HttpStatus.BAD_REQUEST, "Integrity constraint violated.", errorDetails);
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }

    //When request is missing parameter
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exception,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = exception.getParameterName() + " parameter is missing.";
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", exception.getLocalizedMessage());
        CustomExceptionResponse errorResponse = new CustomExceptionResponse(HttpStatus.BAD_REQUEST, message, errorDetails);
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }

    //When method argument is not the expected type:
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put(exception.getName(), exception.getName() + " should be of type " + exception.getRequiredType().getName());
        String message = exception.getLocalizedMessage();
        CustomExceptionResponse errorResponse = new CustomExceptionResponse(HttpStatus.BAD_REQUEST, message, errorDetails);

        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }



    //When validation fails
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errorDetails = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errorDetails.put(fieldName, message);
        });
        CustomExceptionResponse errorResponse = new CustomExceptionResponse(HttpStatus.BAD_REQUEST, "Validation failed.", errorDetails);
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }

    //When we send a requested with an unsupported HTTP method:
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errorDetails = new HashMap<>();
        String message = exception.getLocalizedMessage();

        StringBuilder builder = new StringBuilder();
        builder.append(exception.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        exception.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        errorDetails.put("error", builder.toString());
        CustomExceptionResponse errorResponse = new CustomExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED, message, errorDetails);
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());

    }

    //Handle all other errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Error occured.");
        CustomExceptionResponse errorResponse = new CustomExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(), errorDetails);
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
    }
}
