package com.yasin.blogapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yasin.blogapp.dtos.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        String message = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiExceptionHandler(ApiException exception) {
        String message = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(
            MethodArgumentNotValidException exception) {
        Map<String, String> map = new HashMap<>();
        exception.getAllErrors().forEach((err) -> {
            String field = err.getObjectName();
            String message = err.getDefaultMessage();
            map.put(field, message);
        });
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
