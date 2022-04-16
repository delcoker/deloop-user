package com.deloop.user.api.controllers;


import com.deloop.user.core.models.requests.LoginRequest;
import com.deloop.user.core.models.responses.LoginResponse;
import com.deloop.user.core.services.LoginService;
import com.deloop.user.core.services.user.UserService;
import com.deloop.user.data.api.dtos.UserDto;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@SpringQueryMap LoginRequest loginRequest) {

        LoginResponse response = loginService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    // Authorize with => Bearer <Token>
    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserInfo(Principal principal) throws EmailNotFoundException {
        UserDto user = userService.loadUserByEmail(principal.getName());


//        UserInfo userInfo=new UserInfo();
//        userInfo.setFirstName(userObj.getFirstName());
//        userInfo.setLastName(userObj.getLastName());
//        userInfo.setRoles(userObj.getAuthorities().toArray());

        return ResponseEntity.ok(user);
    }
}
