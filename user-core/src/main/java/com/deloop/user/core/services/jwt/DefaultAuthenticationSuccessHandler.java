package com.deloop.user.core.services.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DefaultAuthenticationSuccessHandler.class);

    private static final int ONE_DAY_MINUTES = 24 * 60;
    private static final String X_SET_AUTHORIZATION_BEARER_HEADER = "X-Set-Authorization-Bearer";

    private final JwtTokenService jwtTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        if (response.containsHeader(X_SET_AUTHORIZATION_BEARER_HEADER)) {
            LOGGER.debug("{} has already been set.", X_SET_AUTHORIZATION_BEARER_HEADER);
            return;
        }

        log.error("success full authentication" + authentication.toString());
        String jwtToken = jwtTokenService.createJwtToken(authentication, ONE_DAY_MINUTES);
        response.setHeader(X_SET_AUTHORIZATION_BEARER_HEADER, jwtToken);
    }
}
