package com.deloop.user.core.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LoginRequest {

    @Builder.Default
    private String email = "";

    @Builder.Default
    private String password = "";

    @Builder.Default
    private String username = "";

    @Builder.Default
    private boolean rememberMe = false;
}
