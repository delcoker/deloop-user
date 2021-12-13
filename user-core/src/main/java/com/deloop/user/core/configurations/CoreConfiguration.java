package com.deloop.user.core.configurations;

import com.deloop.user.core.services.*;
import com.deloop.user.data.config.DBConfiguration;
import com.deloop.user.data.db.repositories.IUserPermissionRepository;
import com.deloop.user.data.db.repositories.IUserRepository;
import com.deloop.user.data.db.repositories.IUserRoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import(DBConfiguration.class)
public class CoreConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
