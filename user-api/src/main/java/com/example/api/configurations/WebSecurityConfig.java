package com.example.api.configurations;

import com.deloop.user.core.services.db.IUserService;
import com.deloop.user.core.services.jwt.JwtAuthenticationEntryPoint;
import com.deloop.user.core.services.jwt.JwtAuthenticationFilter;
import com.deloop.user.core.services.jwt.JwtAuthenticationProvider;
import com.deloop.user.core.services.jwt.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Import({JwtConfig.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {// implements EnvironmentAware {

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private final IUserService userService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(ObjectMapper objectMapper) {
        return new JwtAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationEntryPoint authenticationEntryPoint = lookup("authenticationEntryPoint");
//        JwtTokenService jwtTokenService = lookup("jwtTokenService");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()    // TODO remove soon
//                .authorizeRequests().anyRequest().authenticated() // TODO remove soon
                .and()
//                .authorizeRequests().antMatchers(AUTH_WHITELIST).authenticated()
//                .and()
                .authorizeRequests((request) -> request.antMatchers("/registration/**",
                                "/auth/**", "/swagger-ui/**")
                        .permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenService),
                        UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable().cors().and().headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.authenticationProvider(jwtAuthenticationProvider); // https://www.javadevjournal.com/spring-security/spring-security-multiple-authentication-providers/
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    protected <T> T lookup(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }
}
