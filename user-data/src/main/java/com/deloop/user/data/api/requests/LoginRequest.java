package com.deloop.user.data.api.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LoginRequest {
    private String email;
    private String password;
    private String username;
    private boolean rememberMe;
}
