package com.deloop.user.core.services;


import com.deloop.user.core.models.requests.auth.LoginRequest;
import com.deloop.user.core.models.responses.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);

    String rememberMeToken(String token);
}
