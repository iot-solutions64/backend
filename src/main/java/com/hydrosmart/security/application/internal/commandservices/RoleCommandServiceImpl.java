package com.hydrosmart.security.application.internal.commandservices;

import com.hydrosmart.security.domain.model.commands.SeedRolesCommand;
import com.hydrosmart.security.domain.model.entities.Role;
import com.hydrosmart.security.domain.model.valueobjects.Roles;
import com.hydrosmart.security.domain.services.commandservices.RoleCommandService;
import com.hydrosmart.security.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <h3>Role Command Service Implementation</h3>
 * Implementation of {@link RoleCommandService} to handle {@link SeedRolesCommand}
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * This method will handle the {@link SeedRolesCommand} and will create the roles if not exists
     * @param command {@link SeedRolesCommand}
     * @see SeedRolesCommand
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        } );
    }
}
