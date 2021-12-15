package com.deloop.user.data.auth.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by delcoker on 26/04/2020.
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

//    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";

    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = getAuthentication(request);
        if (authentication == null) {
            log.error("authentication is null");
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        try {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(authorizationHeader)) {
            log.error("Authorization header is empty.");
            return null;
        }

        log.warn("authorizationHeader " + authorizationHeader);

        if (!StringUtils.substringMatch(authorizationHeader, 0, TOKEN_PREFIX)) {
            log.error("Token prefix {} not found in Authorization header.", TOKEN_PREFIX);
            return null;
        }

        String jwtToken = authorizationHeader.substring(TOKEN_PREFIX.length() + 1);

        log.warn(jwtToken);

        try {
            return jwtTokenService.parseJwtToken(jwtToken);
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
