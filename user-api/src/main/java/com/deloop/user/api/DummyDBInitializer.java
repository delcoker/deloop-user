package com.deloop.user.api;

import com.deloop.user.core.configurations.CoreConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Slf4j
@Configuration
@Import({CoreConfig.class})
public class DummyDBInitializer {

    @Value("${db.executeddl}")
    private boolean db_execute_ddl;

//    @Bean
//    CommandLineRunner run(UserService userService, UserRoleService userRoleService,
//                          IUserPermissionService userPermissionService, ILicenseTypeService licenseTypeService) {
//        return args -> {
//            String message = "API => EXECUTE DDL (Reset DB) => " + db_execute_ddl;
//            System.err.println(message);
//            log.warn(message);
//            if (db_execute_ddl) {
//                userPermissionService.addUserPermission(UserPermissionRequest.builder().name("SUPER_PERM_1").code("00").build());
//                userPermissionService.addUserPermission(UserPermissionRequest.builder().name("SUPER_PERM_2").code("11").build());
//
//                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("st").description("starter").name("starter").status(LicenseStatus.ENABLED).build());
//                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("ba").description("basic").name("basic").status(LicenseStatus.ENABLED).build());
//                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("br").description("bronze").name("bronze").status(LicenseStatus.ENABLED).build());
//                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("sl").description("silver").name("silver").status(LicenseStatus.ENABLED).build());
//                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("go").description("gold").name("gold").status(LicenseStatus.ENABLED).build());
//                licenseTypeService.addLicenseType(LicenseTypeRequest.builder().access("pl").description("platinum").name("platinum").status(LicenseStatus.ENABLED).build());
//
//                userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_USER").status(RoleStatus.ENABLED).build());
//                userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_MANAGER").status(RoleStatus.ENABLED).build());
//                userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_ADMIN").status(RoleStatus.ENABLED).build());
//                userRoleService.addUserRole(UserRoleRequest.builder().name("ROLE_SUPER_ADMIN").status(RoleStatus.ENABLED).build());
//
//                UserRole userRole = userRoleService.getUserRoleByName(UserRoleRequest.builder().name("ROLE_ADMIN").build()).get();
//
//                userService.addUser(UserRequest.builder().email("delcoker@gmail.com").username("del").password("123").isVerified(true)
//                        .licenseType(LicenseType.builder().id(1).build())
//                        .userRole(userRoleService.getUserRoleByName(UserRoleRequest.builder().name("ROLE_SUPER_ADMIN").build()).get())
//                        .status(UserStatus.ENABLED)
//                        .build());
//                userService.addUser(UserRequest.builder().email("a@a.com").username("aaa").password("123")
//                        .licenseType(LicenseType.builder().id(2).build())
//                        .userRole(userRole)
//                        .status(UserStatus.DISABLED)
//                        .build());
//            }
//        };
//    }
}
