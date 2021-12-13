package com.deloop.user.data.config;

import com.deloop.user.data.db.repositories.*;
import com.deloop.user.data.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CoreConfiguration {

    @Bean
    IUserService userService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Bean
    IUserRoleService userRoleService(IUserRoleRepository userRoleRepository) {
        return new UserRoleServiceImpl(userRoleRepository);
    }

    @Bean
    IUserPermissionService userPermissionService(IUserPermissionRepository userPermissionRepository) {
        return new UserPermisionServiceImpl(userPermissionRepository);
    }

}
