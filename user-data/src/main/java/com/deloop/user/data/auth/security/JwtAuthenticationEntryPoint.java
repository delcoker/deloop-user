package com.deloop.user.data.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by delcoker on 26/04/2020.
 */
public class JwtAuthenticationEntryPoint extends DefaultAuthenticationFailureHandler
        implements AuthenticationEntryPoint {

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        onAuthenticationFailure(request, response, authException);
    }
}
