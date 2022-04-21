package com.deloop.user.core.services.role;

import com.deloop.user.core.models.requests.AddUserRoleRequest;
import com.deloop.user.data.db.models.UserRole;

import java.util.Optional;

public interface UserRoleService {
    void addUserRole(AddUserRoleRequest addUserRoleRequest);

    Optional<UserRole> getUserRoleById(AddUserRoleRequest addUserRoleRequest);

    Optional<UserRole> getUserRoleByName(AddUserRoleRequest addUserRoleRequest);
}