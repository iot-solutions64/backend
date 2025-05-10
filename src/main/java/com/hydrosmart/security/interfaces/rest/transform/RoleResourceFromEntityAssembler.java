package com.hydrosmart.security.interfaces.rest.transform;

import com.hydrosmart.security.domain.model.entities.Role;
import com.hydrosmart.security.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
