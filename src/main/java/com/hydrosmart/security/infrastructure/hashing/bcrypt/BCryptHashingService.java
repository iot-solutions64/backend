package com.hydrosmart.security.infrastructure.hashing.bcrypt;

import com.hydrosmart.security.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This interface is a marker interface for the BCrypt hashing service.
 * It extends the {@link HashingService} and {@link PasswordEncoder} interfaces.
 * This interface is used to inject the BCrypt hashing service in the {@link HashingService} class.
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
