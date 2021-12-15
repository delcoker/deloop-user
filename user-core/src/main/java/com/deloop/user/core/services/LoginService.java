package com.deloop.user.core.services;

import com.deloop.user.data.api.requests.LoginRequest;
import com.deloop.user.data.api.responses.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);

    String rememberMeToken(String token);
}
