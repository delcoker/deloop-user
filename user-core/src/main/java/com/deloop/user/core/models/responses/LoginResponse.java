package com.deloop.user.core.models.responses;

import lombok.Builder;
import lombok.Getter;

/**
 * Getter prevents this error
 * "message": "No converter for [class com.deloop.user.core.models.responses.LoginResponse] with preset Content-Type 'null'",
 * "path": "/auth/login"
 */
@Getter
//@Setter
@Builder
public class LoginResponse {
    @Builder.Default
    private String token = "no token";
}
