package com.deloop.user.api;

import com.deloop.user.core.config.CoreConfig;
import com.deloop.user.core.models.requests.AddUserPermissionRequest;
import com.deloop.user.core.models.requests.AddUserRequest;
import com.deloop.user.core.models.requests.AddUserRoleRequest;
import com.deloop.user.core.models.requests.LicenseTypeRequest;
import com.deloop.user.core.services.LicenseTypeService;
import com.deloop.user.core.services.role.IUserPermissionService;
import com.deloop.user.core.services.role.UserRoleService;
import com.deloop.user.core.services.user.UserService;
import com.deloop.user.data.db.enums.LicenseStatus;
import com.deloop.user.data.db.enums.RoleStatus;
import com.deloop.user.data.db.enums.UserStatus;
import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.models.UserPermission;
import com.deloop.user.data.db.models.UserRole;
import com.deloop.user.data.db.models.UserRolePermission;
import com.deloop.user.data.db.repositories.roles.UserRolePermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Slf4j
@Configuration
@Import({CoreConfig.class})
public class DummyDBInitializer {

    @Value("${db.seed}")
    private boolean db_seed;

    @Value("${db.executeddl}")
    private boolean db_refresh;

    @Bean
    CommandLineRunner run(UserService userService, UserRoleService userRoleService,
                          IUserPermissionService userPermissionService, LicenseTypeService licenseTypeService,
                          UserRolePermissionRepository userRolePermissionRepository) {
        return args -> {
            String message = "API => EXECUTE DDL (Reset DB) => " + (db_seed || db_refresh);
            System.err.println(message);
            log.warn(message);
            if (db_seed || db_refresh) {
                userPermissionService.addUserPermission(AddUserPermissionRequest.builder().name("Add user").code("00").build());
                userPermissionService.addUserPermission(AddUserPermissionRequest.builder().name("View user").code("11").build());

                userRoleService.addUserRole(AddUserRoleRequest.builder().name("USER").status(RoleStatus.ENABLED).build());
                userRoleService.addUserRole(AddUserRoleRequest.builder().name("MANAGER").status(RoleStatus.ENABLED).build());
                userRoleService.addUserRole(AddUserRoleRequest.builder().name("ADMIN").status(RoleStatus.ENABLED).build());
//                userRoleService.addUserRole(AddUserRoleRequest.builder().name("ROLE_SUPER_ADMIN").status(RoleStatus.ENABLED).build());

                userRolePermissionRepository.save(UserRolePermission.builder()
                        .role(UserRole.builder().id(1).build())
                        .permission(UserPermission.builder().id(1).build())
                        .build());
                userRolePermissionRepository.save(UserRolePermission.builder()
                        .role(UserRole.builder().id(1).build())
                        .permission(UserPermission.builder().id(2).build())
                        .build());

                userRolePermissionRepository.save(UserRolePermission.builder()
                        .role(UserRole.builder().id(2).build())
                        .permission(UserPermission.builder().id(1).build())
                        .build());
                userRolePermissionRepository.save(UserRolePermission.builder()
                        .role(UserRole.builder().id(2).build())
                        .permission(UserPermission.builder().id(2).build())
                        .build());

                userRolePermissionRepository.save(UserRolePermission.builder()
                        .role(UserRole.builder().id(3).build())
                        .permission(UserPermission.builder().id(1).build())
                        .build());
                userRolePermissionRepository.save(UserRolePermission.builder()
                        .role(UserRole.builder().id(3).build())
                        .permission(UserPermission.builder().id(2).build())
                        .build());

                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("st").description("starter").name("starter").status(LicenseStatus.ENABLED).build());
                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("ba").description("basic").name("basic").status(LicenseStatus.ENABLED).build());
                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("br").description("bronze").name("bronze").status(LicenseStatus.ENABLED).build());
                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("sl").description("silver").name("silver").status(LicenseStatus.ENABLED).build());
                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("go").description("gold").name("gold").status(LicenseStatus.ENABLED).build());
                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("pl").description("platinum").name("platinum").status(LicenseStatus.ENABLED).build());

                UserRole userRole = userRoleService.getUserRoleByName(AddUserRoleRequest.builder().name("ADMIN").build()).get();

                userService.addUser(AddUserRequest.builder().email("delcoker@gmail.com").username("del").password("123").isVerified(true)
                        .licenseType(LicenseType.builder().id(1).build())
                        .userRole(userRoleService.getUserRoleByName(AddUserRoleRequest.builder().name("ADMIN").build()).get())
                        .status(UserStatus.ENABLED)
                        .build());
                userService.addUser(AddUserRequest.builder().email("a@a.com").username("aaa").password("123")
                        .licenseType(LicenseType.builder().id(2).build())
                        .userRole(userRole)
                        .status(UserStatus.DISABLED)
                        .build());
            }
        };
    }
}
