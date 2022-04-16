package com.deloop.user.core.models.requests;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LoginRequest {

    @Builder.Default
    @ApiParam(required = true, example = "delcoker@gmail.com")
    private String email = "";

    @Builder.Default
    @ApiParam(example = "123", required = true)
    private String password = "";

    @Builder.Default
    private String username = "";

    @Builder.Default
    @ApiParam(example = "false")
    private boolean rememberMe = false;
}
