package com.deloop.user.api.controllers;


import com.deloop.user.core.models.requests.userdetails.AddOrUpdateUserDetailRequest;
import com.deloop.user.core.services.user.IUserDetailsService;
import com.deloop.user.core.services.user.UserService;
import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.api.dtos.UserDto;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.NoSuchUserException;
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
@RequestMapping("/user/details")
public class UserDetailsController {

    private final IUserDetailsService userDetailsService;
    private final UserService userService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailDto> add(@SpringQueryMap AddOrUpdateUserDetailRequest addOrUpdateUserDetailRequest) throws NoSuchUserException,
            EmailNotFoundException {
//
        return ResponseEntity.ok(userDetailsService.addUserDetails(addOrUpdateUserDetailRequest));
    }

    // Authorize with => Bearer <Token>
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserInfo(Principal principal) throws EmailNotFoundException {
        UserDto user = userService.loadUserByEmail(principal.getName());
        return ResponseEntity.ok(user);
    }
}
