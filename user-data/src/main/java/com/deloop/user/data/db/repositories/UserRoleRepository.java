package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.UserRole;
import com.deloop.user.data.db.models.query.QUserRole;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserRoleRepository implements IUserRoleRepository {
    private final Database db;

    @Override
    public Optional<UserRole> findBy(long id) {
        return Optional.ofNullable(new QUserRole(db).id.eq(id).findOne());
    }

    @Override
    public Optional<UserRole> findByName(String name) {
        return Optional.ofNullable(new QUserRole(db).name.eq(name).findOne());
    }

    @Override
    public void save(UserRole userRole) {
        db.save(userRole);
    }

    @Override
    public UserRole update(UserRole userRole) {
        Optional<UserRole> existing = findBy(userRole.getId());
        if (existing.isEmpty()) {
            String message = "User role with id " + userRole.getId() + " does not exist!";
            log.info(message);
            throw new IllegalStateException(message);
        }
        db.update(userRole);
        return userRole;
    }

    @Override
    public boolean delete(long userRoleId) {
        if (db.delete(UserRole.builder().id(userRoleId).build())) {
            return true;
        }
        String message = "User role with id " + userRoleId + " does not exist!";
        log.error(message);
        return false;
    }
}
