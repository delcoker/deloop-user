package com.deloop.user.core.services;

import com.deloop.user.core.models.requests.UserRoleRequest;
import com.deloop.user.data.db.models.UserRole;

import java.util.Optional;

public interface UserRoleService {
    void addUserRole(UserRoleRequest userRoleRequest);

    Optional<UserRole> getUserRoleById(UserRoleRequest userRoleRequest);

    Optional<UserRole> getUserRoleByName(UserRoleRequest userRoleRequest);
}