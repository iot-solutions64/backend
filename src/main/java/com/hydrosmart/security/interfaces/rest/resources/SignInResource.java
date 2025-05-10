package com.hydrosmart.security.interfaces.rest.resources;

public record SignInResource(
        String username,
        String password
) {
}
