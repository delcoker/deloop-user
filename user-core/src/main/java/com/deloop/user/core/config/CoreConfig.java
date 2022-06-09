package com.deloop.user.core.config;

import com.deloop.user.core.services.*;
import com.deloop.user.core.services.auth.*;
import com.deloop.user.core.services.email.EmailSenderService;
import com.deloop.user.core.services.email.EmailValidatorService;
import com.deloop.user.core.services.jwt.ConfirmationTokenService;
import com.deloop.user.core.services.jwt.ConfirmationTokenServiceImpl;
import com.deloop.user.core.services.jwt.JwtTokenService;
import com.deloop.user.core.services.role.IUserPermissionService;
import com.deloop.user.core.services.role.UserPermisionServiceImpl;
import com.deloop.user.core.services.role.UserRoleService;
import com.deloop.user.core.services.role.UserRoleServiceImpl;
import com.deloop.user.core.services.user.IUserDetailsService;
import com.deloop.user.core.services.user.UserDetailsServiceImpl;
import com.deloop.user.core.services.user.UserService;
import com.deloop.user.core.services.user.UserServiceImpl;
import com.deloop.user.data.config.DBConfiguration;
import com.deloop.user.data.db.repositories.*;
import com.deloop.user.data.db.repositories.roles.IUserPermissionRepository;
import com.deloop.user.data.db.repositories.roles.IUserRoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@Import({WebSecurityConfig.class, DBConfiguration.class, EmailConfig.class, SpringFoxConfig.class})
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
    ConfirmationTokenService confirmationTokenService(IConfirmationTokenRepository confirmationTokenRepository) {
        return new ConfirmationTokenServiceImpl(confirmationTokenRepository);
    }

    @Bean
    UrlService urlService() {
        return new UrlServiceImpl();
    }

    @Bean
    public RegistrationService registrationService(UserService userService, EmailValidatorService emailValidatorService,
                                                   ConfirmationTokenService confirmationTokenService,
                                                   EmailSenderService emailSenderService,
                                                   UrlService urlService) {
        return new RegistrationServiceImpl(userService, emailValidatorService, confirmationTokenService, emailSenderService, urlService);
    }

    @Bean
    public AddressService addressService(AuthenticationFacade authenticationFacade, AddressRepository addressRepository,
                                         UserService userService, IUserDetailsService userDetailsService) {
        return new AddressServiceImpl(authenticationFacade, addressRepository, userService, userDetailsService);
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
    UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        return new UserServiceImpl(userRepository, passwordEncoder, confirmationTokenService);
    }

    @Bean
    UserRoleService userRoleService(IUserRoleRepository userRoleRepository, IUserPermissionRepository userPermissionRepository) {
        return new UserRoleServiceImpl(userRoleRepository, userPermissionRepository);
    }

    @Bean
    IUserPermissionService userPermissionService(IUserPermissionRepository userPermissionRepository) {
        return new UserPermisionServiceImpl(userPermissionRepository);
    }

    @Bean
    LicenseTypeService licenseTypeService(ILicenceTypeRepository licenceTypeRepository) {
        return new LicenseTypeServiceImpl(licenceTypeRepository);
    }
}
