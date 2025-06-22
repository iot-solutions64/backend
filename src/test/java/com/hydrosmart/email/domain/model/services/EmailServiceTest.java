package com.hydrosmart.email.domain.model.services;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    @Test
    void testSendPasswordResetEmail() {
        // Arrange
        JavaMailSender mockMailSender = Mockito.mock(JavaMailSender.class);
        EmailService emailService = new EmailService();
        emailService.mailSender = mockMailSender;

        String recipient = "test@example.com";
        String token = "testToken";
        String expectedResetUrl = "http://localhost:8080/password-reset/confirm?token=" + token;

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        // Act
        emailService.sendPasswordResetEmail(recipient, token);

        // Assert
        Mockito.verify(mockMailSender).send(messageCaptor.capture());
        SimpleMailMessage sentMessage = messageCaptor.getValue();

        assertEquals(recipient, sentMessage.getTo()[0]);
        assertEquals("Password Reset Request", sentMessage.getSubject());
        assertEquals("Click the link to reset your password: " + expectedResetUrl, sentMessage.getText());
    }
}