package com.deloop.user.core.services.db;

import com.deloop.user.data.api.requests.UserRoleRequest;
import com.deloop.user.data.db.models.UserRole;

import java.util.Optional;

public interface IUserRoleService {
    void addUserRole(UserRoleRequest userRoleRequest);

    Optional<UserRole> getUserRoleById(UserRoleRequest userRoleRequest);

    Optional<UserRole> getUserRoleByName(UserRoleRequest userRoleRequest);
}