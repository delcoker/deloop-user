package com.deloop.user.data.auth.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by delcoker on 26/04/2020.
 */
public interface IJwtTokenService {

    String createJwtToken(Authentication authentication, int minutes);

    Authentication parseJwtToken(String jwtToken) throws AuthenticationException;
}