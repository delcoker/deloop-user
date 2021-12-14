package com.example.api;

import com.deloop.user.core.services.ILicenseTypeService;
import com.deloop.user.core.services.IUserPermissionService;
import com.deloop.user.core.services.IUserRoleService;
import com.deloop.user.core.services.IUserService;
import com.deloop.user.data.api.requests.LicenseTypeRequest;
import com.deloop.user.data.api.requests.UserPermissionRequest;
import com.deloop.user.data.api.requests.UserRequest;
import com.deloop.user.data.api.requests.UserRoleRequest;
import com.deloop.user.data.db.enums.LicenseStatus;
import com.deloop.user.data.db.enums.RoleStatus;
import com.deloop.user.data.db.enums.UserStatus;
import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.models.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class UserApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }


    @Bean
    CommandLineRunner run(IUserService userService, IUserRoleService userRoleService,
                          IUserPermissionService userPermissionService, ILicenseTypeService licenseTypeService) {
        return args -> {
            userPermissionService.addUserPermission(UserPermissionRequest.builder().name("SUPER_PERM_1").code("00").build());
            userPermissionService.addUserPermission(UserPermissionRequest.builder().name("SUPER_PERM_2").code("11").build());

            licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("id").description("all").name("all").status(LicenseStatus.ENABLED).build());
            licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("id2").description("all2").name("all2").status(LicenseStatus.ENABLED).build());

            userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_ADMIN").status(RoleStatus.ENABLED).build());
            userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_MANAGER").status(RoleStatus.ENABLED).build());
            userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_USER").status(RoleStatus.ENABLED).build());
            userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_SUPER_ADMIN").status(RoleStatus.ENABLED).build());

            UserRole userRole = userRoleService.getUserRoleByName(UserRoleRequest.builder().name("ROLE_ADMIN").build()).get();
            log.error(userRole.getName() + userRole);
            userService.addUser(UserRequest.builder().email("delcoker@gmail.com").username("delcoker").password("123")
                    .licenseType(LicenseType.builder().id(1).build())
                    .userRole(userRoleService.getUserRoleByName(UserRoleRequest.builder().name("ROLE_SUPER_ADMIN").build()).get())
                    .status(UserStatus.DISABLED)
                    .build());
            userService.addUser(UserRequest.builder().email("a@a.com").username("aaa").password("123")
                    .licenseType(LicenseType.builder().id(2).build())
                    .userRole(userRole)
                    .status(UserStatus.DISABLED)
                    .build());
        };
    }
}
