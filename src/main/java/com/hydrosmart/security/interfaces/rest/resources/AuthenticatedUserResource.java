package com.hydrosmart.security.interfaces.rest.resources;

public record AuthenticatedUserResource(
        Long id,
        String username,
        String token
) {}
