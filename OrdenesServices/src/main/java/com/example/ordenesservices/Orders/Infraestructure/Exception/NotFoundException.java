package com.example.ordenesservices.Orders.Infraestructure.Exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}