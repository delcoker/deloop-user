package com.deloop.user.api.controllers;


import com.deloop.user.core.services.auth.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final LoginService loginService;

    @GetMapping("")
    public String basicAuthHome() {
//        log.info(loginRequest.toString());
        return "Basic Auth Home";
    }
}
