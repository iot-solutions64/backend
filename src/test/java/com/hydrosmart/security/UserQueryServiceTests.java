package com.hydrosmart.security;

import com.hydrosmart.security.application.internal.queryservices.UserQueryServiceImpl;
import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.domain.model.queries.GetAllUsersQuery;
import com.hydrosmart.security.domain.model.queries.GetUserByIdQuery;
import com.hydrosmart.security.domain.model.queries.GetUserByUsernameQuery;
import com.hydrosmart.security.domain.services.queryservices.UserQueryService;
import com.hydrosmart.security.infrastructure.persistence.jpa.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserQueryServiceTests {
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserQueryServiceImpl userQueryService;

    @Test
    public void testFindAllUsers() {
        // Arrange
        User user1 = new User("john", "hashed123");
        User user2 = new User("jane", "hashed456");
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        // Act
        List<User> result = userQueryService.handle(new GetAllUsersQuery());

        // Assert
        assertEquals(2, result.size());
        assertEquals("john", result.get(0).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testUserExistsByIdQuery() {
        // Arrange
        User user = new User("john", "hashed123");
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userQueryService.handle(new GetUserByIdQuery(1L));

        // Assert
        assertTrue(result.isPresent());
        assertEquals("john", result.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testUserDoesNotExistByIdQuery() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<User> result = userQueryService.handle(new GetUserByIdQuery(999L));

        assertTrue(result.isEmpty());
        verify(userRepository).findById(999L);
    }

    @Test
    public void testUserExistsByUsernameQuery() {
        User user = new User("jane", "hashed456");
        when(userRepository.findByUsername("jane")).thenReturn(Optional.of(user));

        Optional<User> result = userQueryService.handle(new GetUserByUsernameQuery("jane"));

        assertTrue(result.isPresent());
        assertEquals("jane", result.get().getUsername());
        verify(userRepository).findByUsername("jane");
    }

    @Test
    public void testUserDoesNotExistByUsernameQuery() {
        when(userRepository.findByUsername("ghost")).thenReturn(Optional.empty());

        Optional<User> result = userQueryService.handle(new GetUserByUsernameQuery("ghost"));

        assertTrue(result.isEmpty());
        verify(userRepository).findByUsername("ghost");
    }
}
