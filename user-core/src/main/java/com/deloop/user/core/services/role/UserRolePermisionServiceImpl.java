package com.deloop.user.core.services.role;

import com.deloop.user.core.models.requests.AddUserRolePermissionRequest;
import com.deloop.user.data.db.models.UserPermission;
import com.deloop.user.data.db.models.UserRole;
import com.deloop.user.data.db.models.UserRolePermission;
import com.deloop.user.data.db.repositories.roles.UserRolePermissionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRolePermisionServiceImpl implements UserRolePermissionService {
    public final UserRolePermissionRepository userRolePermissionRepository;

    public void addUserRolePermission(AddUserRolePermissionRequest addUserRolePermissionRequest) {

        UserRolePermission userRolePermission = UserRolePermission.builder()
                .id(addUserRolePermissionRequest.getId())
                .role(UserRole.builder().id(addUserRolePermissionRequest.getRoleId()).build())
                .permission(UserPermission.builder().id(addUserRolePermissionRequest.getPermissionId()).build())
                .build();
        userRolePermissionRepository.save(userRolePermission);
    }

}
