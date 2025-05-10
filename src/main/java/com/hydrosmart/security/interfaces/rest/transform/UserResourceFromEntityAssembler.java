package com.hydrosmart.security.interfaces.rest.transform;

import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.domain.model.entities.Role;
import com.hydrosmart.security.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
