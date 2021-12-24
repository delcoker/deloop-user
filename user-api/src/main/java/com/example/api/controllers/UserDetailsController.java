package com.example.api.controllers;


import com.deloop.user.core.services.LoginService;
import com.deloop.user.core.services.db.IUserService;
import com.deloop.user.data.api.dtos.UserDto;
import com.deloop.user.data.api.requests.LoginRequest;
import com.deloop.user.data.api.requests.UserRequest;
import com.deloop.user.data.api.responses.LoginResponse;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse response = loginService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserInfo(Principal principal) throws EmailNotFoundException {
        User user = userService.loadUserByEmail(UserRequest.builder().email(principal.getName()).build());

        UserDto userDto = user.getUserDto();

//        UserInfo userInfo=new UserInfo();
//        userInfo.setFirstName(userObj.getFirstName());
//        userInfo.setLastName(userObj.getLastName());
//        userInfo.setRoles(userObj.getAuthorities().toArray());


        return ResponseEntity.ok(userDto);
    }
}
