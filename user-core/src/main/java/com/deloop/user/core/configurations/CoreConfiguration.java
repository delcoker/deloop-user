package com.deloop.user.core.configurations;

import com.deloop.user.core.services.EmailValidatorServiceImpl;
import com.deloop.user.core.services.*;
import com.deloop.user.data.api.requests.LicenseTypeRequest;
import com.deloop.user.data.config.DBConfiguration;
import com.deloop.user.data.db.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import(DBConfiguration.class)
public class CoreConfiguration {

    @Bean
    IConfirmationTokenService confirmationTokenService(IConfirmationTokenRepository confirmationTokenRepository) {
        return new ConfirmationTokenServiceImpl(confirmationTokenRepository);
    }

    @Bean
    EmailValidatorService emailValidatorService() {
        return new EmailValidatorServiceImpl();
    }

    @Bean
    public RegistrationService registrationService(IUserService userService, EmailValidatorService emailValidatorService) {
        return new RegistrationServiceImpl(userService, emailValidatorService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    IUserService userService(IUserRepository userRepository, PasswordEncoder passwordEncoder, IConfirmationTokenService confirmationTokenService) {
        return new UserServiceImpl(userRepository, passwordEncoder, confirmationTokenService);
    }

    @Bean
    IUserRoleService userRoleService(IUserRoleRepository userRoleRepository) {
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
