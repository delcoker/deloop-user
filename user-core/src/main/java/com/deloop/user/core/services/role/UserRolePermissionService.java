package com.deloop.user.core.services.role;

import com.deloop.user.core.models.requests.AddUserRolePermissionRequest;

public interface UserRolePermissionService {
    void addUserRolePermission(AddUserRolePermissionRequest addUserRolePermissionRequest);

//    Optional<UserRole> getUserRoleById(AddUserRoleRequest addUserRoleRequest);
//
//    Optional<UserRole> getUserRoleByName(AddUserRoleRequest addUserRoleRequest);
}