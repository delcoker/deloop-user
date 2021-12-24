package com.deloop.user.core.services.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Base64;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.lang.System.currentTimeMillis;

/**
 * Created by delcoker on 26/04/2020.
 */
public class JwtTokenServiceImpl implements JwtTokenService {

    private static final String AUTHORITIES = "authorities";
    private final byte[] secretKey;// = new byte[]{127, -128};

    private final long EXPIRATION_SECONDS = 60 * 1000;
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = HS512;

    public JwtTokenServiceImpl(String secretKey) {
        this.secretKey = Base64.getDecoder().decode(secretKey);
    }

    @Override
    public String createJwtToken(Authentication authentication, int minutes) {
//        Claims claims = Jwts.claims()
//                .setId(String.valueOf(IdentityGenerator.generate()))
//                .setSubject(authentication.getName())
//                .setExpiration(new Date(currentTimeMillis() + EXPIRATION_SECONDS * minutes))
//                .setIssuedAt(new Date());
//
//        String authorities = authentication.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .map(String::toUpperCase)
//                .collect(Collectors.joining(","));
//
//        claims.put(AUTHORITIES, authorities);
//
//        String accessToken = Jwts.builder()
//                .setClaims(claims)
//                .signWith(HS512, secretKey)
//                .compact();

        String accessToken = Jwts.builder()
                .setIssuer("appName")
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentTimeMillis() + EXPIRATION_SECONDS * minutes))
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();

//        Claims refreshClaims = Jwts.claims()
//                .setId(String.valueOf(IdentityGenerator.generate()))
//                .setSubject(authentication.getName())
//                .setExpiration(new Date(currentTimeMillis() + EXPIRATION_SECONDS * minutes))
//                .setIssuedAt(new Date());
//
//        String refreshToken = Jwts.builder()
//                .setClaims(refreshClaims)
//                .signWith(HS512, secretKey)
//                .compact();
//
//        System.err.println(accessToken);

        return accessToken;
    }

    @Override
    public Authentication parseJwtToken(String jwtToken) throws AuthenticationException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken)
                    .getBody();

            return JwtAuthenticationToken.of(claims);
        } catch (ExpiredJwtException | SignatureException e) {
            throw new BadCredentialsException(e.getMessage(), e);
        } catch (UnsupportedJwtException | MalformedJwtException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e);
        }
    }

}
