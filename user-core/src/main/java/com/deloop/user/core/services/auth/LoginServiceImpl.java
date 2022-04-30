package com.deloop.user.core.services.auth;

import com.deloop.user.core.models.requests.auth.LoginRequest;
import com.deloop.user.core.models.responses.LoginResponse;
import com.deloop.user.core.services.jwt.JwtTokenService;
import io.ebean.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Value("${jwt.expiration_minutes}")
    private static int EXPIRATION_MINUTES;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authReq);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        String jwtToken = jwtTokenService.createJwtToken(authentication, EXPIRATION_MINUTES);

        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    @Transactional
    public String rememberMeToken(String token) {
//        ConfirmationToken confirmationToken = confirmationTokenService
//                .getToken(token)
//                .orElseThrow(() -> new IllegalStateException("token not found"));
//
//        if (confirmationToken.getConfirmedAt() != null) {
//            throw new IllegalStateException("email already confirmed");
//        }
//
//        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
//
//        if (expiredAt.isBefore(LocalDateTime.now())) {
//            throw new IllegalStateException("token expired");
//        }
//
//        confirmationTokenService.setConfirmedAt(token);
//        userService.verifyUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
