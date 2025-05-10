package com.hydrosmart.security.interfaces.rest.transform;

import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
