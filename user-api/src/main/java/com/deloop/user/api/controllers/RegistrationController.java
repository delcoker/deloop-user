package com.deloop.user.api.controllers;


import com.deloop.user.core.models.requests.RegistrationRequest;
import com.deloop.user.core.services.RegistrationService;
import com.deloop.user.data.exceptions.EmailInvalidException;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@SpringQueryMap RegistrationRequest registrationRequest) throws EmailInvalidException, EmailIsAlreadyTakenException {
        log.info(registrationRequest.toString());

        if (!registrationRequest.isValid()) {
            throw new EmailInvalidException("Emails do not match");
        }

        return ResponseEntity.ok(registrationService.register(registrationRequest));
    }

    @GetMapping(path = "confirm")
    public String confirm(@SpringQueryMap String token) {
        return registrationService.confirmToken(token);
    }

}
