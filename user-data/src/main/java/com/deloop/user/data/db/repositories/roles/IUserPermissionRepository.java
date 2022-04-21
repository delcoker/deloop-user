package com.deloop.user.data.db.repositories.roles;

import com.deloop.user.data.db.models.UserPermission;

import java.util.Optional;

public interface IUserPermissionRepository {

    Optional<UserPermission> findBy(long id);

    Optional<UserPermission> findByName(String name);

    Optional<UserPermission> findByCode(String code);

    void save(UserPermission userPermission);

    UserPermission update(UserPermission userPermission);

    boolean delete(long userPermissionId);
}
