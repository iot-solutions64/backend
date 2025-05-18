package com.hydrosmart.security;

import com.hydrosmart.security.application.internal.commandservices.UserCommandServiceImpl;
import com.hydrosmart.security.application.internal.outboundservices.hashing.HashingService;
import com.hydrosmart.security.application.internal.outboundservices.tokens.TokenService;
import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.domain.model.commands.SignInCommand;
import com.hydrosmart.security.domain.model.commands.SignUpCommand;
import com.hydrosmart.security.domain.model.entities.Role;
import com.hydrosmart.security.domain.model.valueobjects.Roles;
import com.hydrosmart.security.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.hydrosmart.security.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserCommandServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock private HashingService hashingService;
    @Mock private TokenService tokenService;
    @Mock private RoleRepository roleRepository;

    @InjectMocks
    private UserCommandServiceImpl userCommandService;

    private final String username = "testuser";
    private final String rawPassword = "password123";
    private final String hashedPassword = "hashed123";
    private final String token = "mockedToken";

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(username, hashedPassword);
        user.setId(1L);
        user.setRoles(Set.of(new Role(Roles.ROLE_USER)));
    }

    @Test
    void testHandleSignInSuccess() {
        // Arrange
        SignInCommand command = new SignInCommand(username, rawPassword);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(hashingService.matches(rawPassword, hashedPassword)).thenReturn(true);
        when(tokenService.generateToken(username)).thenReturn(token);

        // Act
        Optional<ImmutablePair<User, String>> result = userCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(username, result.get().getLeft().getUsername());
        assertEquals(token, result.get().getRight());
    }

    @Test
    void testHandleSignInUserNotFound() {
        SignInCommand command = new SignInCommand("nonexistent", rawPassword);
        when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userCommandService.handle(command);
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testHandleSignInInvalidPassword() {
        SignInCommand command = new SignInCommand(username, "wrongPassword");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(hashingService.matches("wrongPassword", hashedPassword)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userCommandService.handle(command);
        });
        assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    void testHandleSignUpSuccess() {
        SignUpCommand command = new SignUpCommand(username, rawPassword, List.of(new Role(Roles.ROLE_USER)));

        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(roleRepository.findByName(Roles.ROLE_USER)).thenReturn(Optional.of(new Role(Roles.ROLE_USER)));
        when(hashingService.encode(rawPassword)).thenReturn(hashedPassword);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
    }

    @Test
    void testHandleSignUpUsernameExists() {
        SignUpCommand command = new SignUpCommand(username, rawPassword, List.of(new Role(Roles.ROLE_USER)));
        when(userRepository.existsByUsername(username)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userCommandService.handle(command);
        });

        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    void testHandleSignUpInvalidRole() {
        SignUpCommand command = new SignUpCommand(username, rawPassword, List.of(new Role(Roles.ROLE_ADMIN)));
        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(roleRepository.findByName(Roles.ROLE_ADMIN)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userCommandService.handle(command);
        });

        assertEquals("Role title not found", exception.getMessage());
    }
}