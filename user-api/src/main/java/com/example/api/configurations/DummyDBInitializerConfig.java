package com.example.api.configurations;

import com.deloop.user.core.configurations.CoreConfiguration;
import com.deloop.user.core.services.db.ILicenseTypeService;
import com.deloop.user.core.services.db.IUserPermissionService;
import com.deloop.user.core.services.db.IUserRoleService;
import com.deloop.user.core.services.db.IUserService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Slf4j
@Configuration
@Import({CoreConfiguration.class})
public class DummyDBInitializerConfig {

    @Value("${db.executeddl}")
    private boolean db_execute_ddl;

    @Bean
    CommandLineRunner run(IUserService userService, IUserRoleService userRoleService,
                          IUserPermissionService userPermissionService, ILicenseTypeService licenseTypeService) {
        return args -> {
            String message = "API => EXECUTE DDL (Reset DB) => " + db_execute_ddl;
            System.err.println(message);
            log.warn(message);
            if (db_execute_ddl) {
                userPermissionService.addUserPermission(UserPermissionRequest.builder().name("SUPER_PERM_1").code("00").build());
                userPermissionService.addUserPermission(UserPermissionRequest.builder().name("SUPER_PERM_2").code("11").build());

                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("id").description("all").name("all").status(LicenseStatus.ENABLED).build());
                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("id2").description("all2").name("all2").status(LicenseStatus.ENABLED).build());

                userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_ADMIN").status(RoleStatus.ENABLED).build());
                userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_MANAGER").status(RoleStatus.ENABLED).build());
                userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_USER").status(RoleStatus.ENABLED).build());
                userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_SUPER_ADMIN").status(RoleStatus.ENABLED).build());

                UserRole userRole = userRoleService.getUserRoleByName(UserRoleRequest.builder().name("ROLE_ADMIN").build()).get();

                userService.addUser(UserRequest.builder().email("delcoker@gmail.com").username("delcoker").password("123").isVerified(true)
                        .licenseType(LicenseType.builder().id(1).build())
                        .userRole(userRoleService.getUserRoleByName(UserRoleRequest.builder().name("ROLE_SUPER_ADMIN").build()).get())
                        .status(UserStatus.ENABLED)
                        .build());
                userService.addUser(UserRequest.builder().email("a@a.com").username("aaa").password("123")
                        .licenseType(LicenseType.builder().id(2).build())
                        .userRole(userRole)
                        .status(UserStatus.DISABLED)
                        .build());
            }
        };
    }
}
