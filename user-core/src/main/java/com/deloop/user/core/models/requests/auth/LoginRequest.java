package com.deloop.user.core.models.requests.auth;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LoginRequest {

    @Builder.Default
    @ApiParam(required = true, value = "delcoker@gmail.com", example = "delcoker@gmail.com")
    private String email = "";

    @Builder.Default
    @ApiParam(required = true, example = "123")
    private String password = "";

    @Builder.Default
    private String username = "";

    @Builder.Default
    @ApiParam(value = "false")
    private boolean rememberMe = false;
}
