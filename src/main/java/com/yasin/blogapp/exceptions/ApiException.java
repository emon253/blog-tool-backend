package com.yasin.blogapp.exceptions;


public class ApiException extends RuntimeException {

    public ApiException() {

    }

    public ApiException(String message) {
        super(message);
    }

}
