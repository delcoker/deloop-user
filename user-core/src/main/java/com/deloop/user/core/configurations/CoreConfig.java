package com.deloop.user.core.configurations;

import com.deloop.user.core.services.*;
import com.deloop.user.core.services.auth.AuthenticationFacade;
import com.deloop.user.core.services.auth.AuthenticationFacadeImpl;
import com.deloop.user.core.services.email.EmailSenderService;
import com.deloop.user.core.services.email.EmailValidatorService;
import com.deloop.user.core.services.jwt.JwtTokenService;
import com.deloop.user.core.services.user.*;
import com.deloop.user.data.config.DBConfiguration;
import com.deloop.user.data.db.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@Import({DBConfiguration.class, EmailConfig.class})
public class CoreConfig {
    @Bean
    AuthenticationFacade authenticationFacade() {
        return new AuthenticationFacadeImpl();
    }

    @Bean
    IUserDetailsService userDetailsService(UserDetailsRepository userDetailRepository, AuthenticationFacade authenticationFacade,
                                           UserService userService) {
        return new UserDetailsServiceImpl(userDetailRepository, authenticationFacade, userService);
    }

    @Bean
    IConfirmationTokenService confirmationTokenService(IConfirmationTokenRepository confirmationTokenRepository) {
        return new ConfirmationTokenServiceImpl(confirmationTokenRepository);
    }

    @Bean
    public RegistrationService registrationService(UserService userService, EmailValidatorService emailValidatorService,
                                                   IConfirmationTokenService confirmationTokenService, EmailSenderService emailSenderService) {
        return new RegistrationServiceImpl(userService, emailValidatorService, confirmationTokenService, emailSenderService);
    }

    @Bean
    public LoginService loginService(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        return new LoginServiceImpl(authenticationManager, jwtTokenService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, IConfirmationTokenService confirmationTokenService) {
        return new UserServiceImpl(userRepository, passwordEncoder, confirmationTokenService);
    }

    @Bean
    UserRoleService userRoleService(IUserRoleRepository userRoleRepository) {
        return new UserRoleServiceImpl(userRoleRepository);
    }

    @Bean
    IUserPermissionService userPermissionService(IUserPermissionRepository userPermissionRepository) {
        return new UserPermisionServiceImpl(userPermissionRepository);
    }

    @Bean
    ILicenseTypeService licenseTypeService(ILicenceTypeRepository licenceTypeRepository) {
        return new LicenseTypeServiceImpl(licenceTypeRepository);
    }
}
