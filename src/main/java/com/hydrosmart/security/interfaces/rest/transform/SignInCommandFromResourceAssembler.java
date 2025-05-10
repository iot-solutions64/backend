package com.hydrosmart.security.interfaces.rest.transform;

import com.hydrosmart.security.domain.model.commands.SignInCommand;
import com.hydrosmart.security.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
