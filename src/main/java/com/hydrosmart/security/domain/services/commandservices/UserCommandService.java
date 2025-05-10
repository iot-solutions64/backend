package com.hydrosmart.security.domain.services.commandservices;

import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.domain.model.commands.SignInCommand;
import com.hydrosmart.security.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);
}
