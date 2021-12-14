package com.example.api.configurations;

//import org.springframework.context.annotation.Configuration;

import com.deloop.user.core.configurations.CoreConfiguration;
import com.deloop.user.data.config.DBRepositoryConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreConfiguration.class})
public class OtherConfig {

//    @Bean
//    IUserService userService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return new UserServiceImpl(userRepository, passwordEncoder);
//    }
//
//    @Bean
//    IUserPermissionService userPermissionService(IUserPermissionRepository userPermissionRepository) {
//        return new UserPermisionServiceImpl(userPermissionRepository);
//    }
//
//    @Bean
//    IUserRoleService userRoleService(IUserRoleRepository userRoleRepository) {
//        return new UserRoleServiceImpl(userRoleRepository);
//    }
//
//    @Bean
//    IUserTypeService userTypeService(IUserTypeRepository userTypeRepository) {
//        return new UserTypeServiceImpl(userTypeRepository);
//    }

}
