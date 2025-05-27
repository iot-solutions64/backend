package com.hydrosmart.email.interfaces.rest.controllers;

import com.hydrosmart.email.domain.model.entities.PasswordResetToken;
import com.hydrosmart.email.domain.model.services.EmailService;
import com.hydrosmart.security.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;


@RestController
@RequestMapping("/password-reset")
public class PasswordResetController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/request")
    public String requestPasswordReset(@RequestParam String username) {
        // Check if the username exists in the database
        if (!userRepository.existsByUsername(username)) {
            return "Error: Username not registered in the application.";
        }

        // Generate a unique token
        String token = UUID.randomUUID().toString();

        // Create and save the token
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(username); // Use the username as the email
        resetToken.setConfirmed(false);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(1));


        // Send the email
        emailService.sendPasswordResetEmail(username, token);

        return "Password reset email sent!";
    }

    @GetMapping("/confirm")
    public String confirmPasswordReset(@RequestParam String token) {
        // Buscar el token en la base de datos (simulado aquí)
        PasswordResetToken resetToken = new PasswordResetToken(); // Simulación
        resetToken.setToken(token);
        resetToken.setConfirmed(true);
        // Aquí deberías actualizar el token en tu repositorio

        return "Password reset confirmed!";
    }
}
