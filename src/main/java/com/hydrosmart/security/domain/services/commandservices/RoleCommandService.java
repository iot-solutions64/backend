package com.hydrosmart.security.domain.services.commandservices;

import com.hydrosmart.security.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
