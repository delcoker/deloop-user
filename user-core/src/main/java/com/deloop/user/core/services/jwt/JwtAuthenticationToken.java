package com.deloop.user.core.services.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by delcoker on 26/04/2020.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final String AUTHORITIES = "authorities";

    //    private final long userId;
    private final String userIdentifier;

    private JwtAuthenticationToken(String userIdentifier, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userIdentifier = userIdentifier;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public String getPrincipal() {
        return userIdentifier;
    }

    /**
     * Factory method for creating a new {@code {@link JwtAuthenticationToken}}.
     *
     * @param claims JWT claims
     * @return a JwtAuthenticationToken
     */
    public static JwtAuthenticationToken of(Claims claims) {
        String userId = claims.getSubject();

        Collection<GrantedAuthority> authorities =
                Arrays.stream(String.valueOf(claims.get(AUTHORITIES)).split(","))
                        .map(String::trim)
                        .filter(StringUtils::hasText)
                        .map(String::toUpperCase)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(userId, authorities);

        Date now = new Date();
        Date expiration = claims.getExpiration();
        Date notBefore = Optional.ofNullable(claims.getNotBefore()).orElse(now);
        jwtAuthenticationToken.setAuthenticated(now.after(notBefore) && now.before(expiration));

        return jwtAuthenticationToken;
    }
}
