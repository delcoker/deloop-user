package com.deloop.user.api.controllers;


import com.deloop.user.core.models.requests.auth.LoginRequest;
import com.deloop.user.core.models.responses.LoginResponse;
import com.deloop.user.core.services.auth.LoginService;
import com.deloop.user.core.services.user.UserService;
import com.deloop.user.data.api.dtos.UserDto;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        if (!loginRequest.isValid()) {
            throw new InvalidParameterException("Email or password empty");
        }
        LoginResponse response = loginService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity.ok(response);
    }

    // Authorize with => Bearer <Token>
    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserInfo(Principal principal) throws EmailNotFoundException {
        UserDto user = userService.loadUserByEmail(principal.getName());
        return ResponseEntity.ok(user);
    }
}
