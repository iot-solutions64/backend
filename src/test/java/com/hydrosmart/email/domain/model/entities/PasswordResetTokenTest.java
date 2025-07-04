package com.hydrosmart.email.domain.model.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PasswordResetTokenTest {

    @Test
    void testPasswordResetTokenGettersAndSetters() {
        // Arrange
        PasswordResetToken token = new PasswordResetToken();
        Long id = 1L;
        String tokenValue = "testToken";
        String email = "test@example.com";
        boolean confirmed = true;
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(1);

        // Act
        token.setId(id);
        token.setToken(tokenValue);
        token.setEmail(email);
        token.setConfirmed(confirmed);
        token.setExpirationDate(expirationDate);

        // Assert
        assertEquals(id, token.getId());
        assertEquals(tokenValue, token.getToken());
        assertEquals(email, token.getEmail());
        assertTrue(token.isConfirmed());
        assertEquals(expirationDate, token.getExpirationDate());
    }
}


