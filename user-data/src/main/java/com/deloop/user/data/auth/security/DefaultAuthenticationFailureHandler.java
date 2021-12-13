
package com.deloop.user.data.auth.security;

import com.deloop.user.data.auth.rest.RestErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAuthenticationFailureHandler.class);

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        LOGGER.warn("IDK {} ",exception.getMessage());

        HttpStatus httpStatus = translateAuthenticationException(exception);

        response.setStatus(httpStatus.value());
        response.setContentType(APPLICATION_JSON_VALUE);

        writeResponse(response.getWriter(), httpStatus, exception);
    }

    protected HttpStatus translateAuthenticationException(AuthenticationException exception) {
        return UNAUTHORIZED;
    }

    protected void writeResponse(Writer writer, HttpStatus httpStatus, AuthenticationException exception)
            throws IOException {

        RestErrorResponse restErrorResponse = RestErrorResponse.of(httpStatus, exception);
        objectMapper.writeValue(writer, restErrorResponse);
    }
}
