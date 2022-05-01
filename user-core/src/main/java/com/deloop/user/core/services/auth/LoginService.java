package com.deloop.user.core.services.auth;


import com.deloop.user.core.models.responses.LoginResponse;

public interface LoginService {
    LoginResponse login(String email, String password);

    String rememberMeToken(String token);
}
