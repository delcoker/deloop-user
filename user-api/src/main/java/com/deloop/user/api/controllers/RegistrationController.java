package com.deloop.user.api.controllers;


import com.deloop.user.core.models.requests.auth.RegistrationRequest;
import com.deloop.user.core.services.auth.RegistrationService;
import com.deloop.user.data.exceptions.EmailInvalidException;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import com.deloop.user.data.exceptions.InvalidConfirmationTokenException;
import com.deloop.user.data.exceptions.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    @Value("${registration_confirmation_redirect_url}")
    private String REGISTRATION_CONFIRMATION_REDIRECT_URL;

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) throws PasswordMismatchException,
            EmailInvalidException, EmailIsAlreadyTakenException {
        log.info(registrationRequest.toString());

        if (!registrationRequest.isValid()) {
            throw new PasswordMismatchException("Passwords do not match!");
        }

        return ResponseEntity.ok(registrationService.register(registrationRequest));
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<Object> confirm(@SpringQueryMap String token) throws InvalidConfirmationTokenException {
        boolean confirmed = registrationService.confirmToken(token);

        if (!confirmed) {
            throw new InvalidConfirmationTokenException("Problem with confirmation token.");
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(REGISTRATION_CONFIRMATION_REDIRECT_URL.trim()))
                .build();
    }

}
