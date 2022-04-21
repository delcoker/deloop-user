package com.deloop.user.data.db.repositories.roles;

import com.deloop.user.data.db.models.UserRolePermission;

import java.util.Optional;

public interface UserRolePermissionRepository {

    Optional<UserRolePermission> findBy(long id);

//    Optional<UserRole> findByName(String name);

    void save(UserRolePermission userRolePermission);

//    UserRole update(UserRole userRole);
//
//    boolean delete(long userRoleId);
}
