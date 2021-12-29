package com.example.api.configurations;

import com.deloop.user.core.services.jwt.JwtAuthenticationProvider;
import com.deloop.user.core.services.jwt.JwtTokenService;
import com.deloop.user.core.services.jwt.JwtTokenServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class JwtConfig { // implements EnvironmentAware {

    @Value("${jwt.secret.key}")
    private String JWT_TOKEN_SECRET_KEY;

    @Value("${remember.me.secret.key}")
    private String REMEMBER_ME_TOKEN_SECRET_KEY;

//    private static final String JWT_TOKEN_SECRET_KEY = "JWT_TOKEN_SECRET_KEY";
//    private static final String REMEMBER_ME_TOKEN_SECRET_KEY = "REMEMBER_ME_TOKEN_SECRET_KEY";
//
//    private Environment environment;
//
//    static {
//        System.setProperty(JWT_TOKEN_SECRET_KEY, "foo1234567fo");
//    }
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//    }

    @Bean
    public JwtTokenService jwtTokenService() {
        String secretKey = getJwtTokenSecretKey().orElseThrow(IllegalStateException::new);
        return new JwtTokenServiceImpl(secretKey);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider();
    }

    protected Optional<String> getJwtTokenSecretKey() {
        return Optional.ofNullable(JWT_TOKEN_SECRET_KEY);
    }

    protected Optional<String> getRememberMeTokenSecretKey() {
        return Optional.ofNullable(REMEMBER_ME_TOKEN_SECRET_KEY);
    }
}
