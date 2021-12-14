package com.deloop.user.data;


import com.deloop.user.data.api.requests.UserPermissionRequest;
import com.deloop.user.data.api.requests.UserRequest;
import com.deloop.user.data.api.requests.UserRoleRequest;
import com.deloop.user.data.db.enums.RoleStatus;
import com.deloop.user.data.db.models.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class} /* purpose of : (exclude = {DataSourceAutoConfiguration.class})*/)
public class UserDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDataApplication.class, args);
    }

//
//    @Bean
//    CommandLineRunner run(IUserService userService, IUserRoleService userRoleService, IUserPermissionService iUserPermissionService) {
//        return args -> {
//            iUserPermissionService.addUserPermission(UserPermissionRequest.builder().name("SUPER_PERM_1").code("00").build());
//            iUserPermissionService.addUserPermission(UserPermissionRequest.builder().name("SUPER_PERM_2").code("11").build());
//
//            userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_ADMIN").status(RoleStatus.ENABLED).build());
//            userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_MANAGER").status(RoleStatus.ENABLED).build());
//            userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_USER").status(RoleStatus.ENABLED).build());
//            userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_SUPER_ADMIN").status(RoleStatus.ENABLED).build());
//
//            UserRole userRole = userRoleService.getUserRoleByName(UserRoleRequest.builder().name("ROLE_ADMIN").build()).get();
//            log.error(userRole.getName() + userRole);
//            userService.addUser(UserRequest.builder().email("delcoker@gmail.com").username("delcoker").password("123").userRole(userRoleService.getUserRoleByName(UserRoleRequest.builder().name("ROLE_SUPER_ADMIN").build()).get()).build());
//            userService.addUser(UserRequest.builder().email("a@a.com").username("aaa").password("123").userRole(userRole).build());
//        };
//    }

}
