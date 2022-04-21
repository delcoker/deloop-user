package com.deloop.user.core.services.role;

import com.deloop.user.core.models.requests.AddUserRoleRequest;
import com.deloop.user.data.db.models.UserRole;
import com.deloop.user.data.db.repositories.roles.IUserPermissionRepository;
import com.deloop.user.data.db.repositories.roles.IUserRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    public final IUserRoleRepository userRoleRepository;

    public final IUserPermissionRepository userPermissionRepository;

    @Override
    public void addUserRole(AddUserRoleRequest addUserRoleRequest) {
        UserRole userRole = UserRole.builder()
                .name(addUserRoleRequest.getName())
                .description(addUserRoleRequest.getDescription())
                .capabilities(addUserRoleRequest.getCapabilities())
                .status(addUserRoleRequest.getStatus())
                .build();

//        UserPermission userPermission = UserPermission.builder()
//                .id(5)
//                .name("Delete role")
//                .code("codeless")
//                .description("test")
//                .build();

//        List<UserRole> userRoles = new ArrayList<>();
//        userRoles.add(userRole);
//        userPermission.getUserRoles().addAll(userRoles);

        userRoleRepository.save(userRole);
    }

    @Override
    public Optional<UserRole> getUserRoleById(AddUserRoleRequest addUserRoleRequest) {
        return userRoleRepository.findBy(addUserRoleRequest.getId());
    }

    @Override
    public Optional<UserRole> getUserRoleByName(AddUserRoleRequest addUserRoleRequest) {
        return userRoleRepository.findByName(addUserRoleRequest.getName());
    }

}
