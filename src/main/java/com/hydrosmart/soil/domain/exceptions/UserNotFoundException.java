package com.hydrosmart.soil.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuario no encontrado o inexistente :(");
    }
}