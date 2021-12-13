package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.UserRole;

import java.util.Optional;

public interface IUserRoleRepository {

    Optional<UserRole> findBy(long id);

    Optional<UserRole> findByName(String name);

    void save(UserRole userRole);

    UserRole update(UserRole userRole);

    boolean delete(long userRoleId);
}
