package com.example.page.exp;

public class AppBadRequestException extends RuntimeException{
    public AppBadRequestException(String message) {
        super(message);
    }
}
