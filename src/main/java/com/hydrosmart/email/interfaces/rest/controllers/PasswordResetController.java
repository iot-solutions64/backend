package com.hydrosmart.email.interfaces.rest.controllers;

import com.hydrosmart.email.domain.model.entities.PasswordResetToken;
import com.hydrosmart.email.domain.model.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;


@RestController
@RequestMapping("/password-reset")
public class PasswordResetController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/request")
    public String requestPasswordReset(@RequestParam String email) {
        // Append @gmail.com if the email does not contain @
        if (!email.contains("@")) {
            email = email + "@gmail.com";
        }

        // Generate a unique token
        String token = UUID.randomUUID().toString();

        // Save the token in the database (simulated here)
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setConfirmed(false);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(1));
        // Save the token in your repository here

        // Send the email
        emailService.sendPasswordResetEmail(email, token);

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
