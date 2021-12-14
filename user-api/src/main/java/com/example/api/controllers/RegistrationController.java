package com.example.api.controllers;


import com.deloop.user.core.services.RegistrationService;
import com.deloop.user.data.api.requests.RegistrationRequest;
import com.deloop.user.data.exceptions.EmailInvalidException;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public String register(@SpringQueryMap RegistrationRequest registrationRequest) throws EmailInvalidException, EmailIsAlreadyTakenException {
        log.info(registrationRequest.toString());
        return registrationService.register(registrationRequest);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
