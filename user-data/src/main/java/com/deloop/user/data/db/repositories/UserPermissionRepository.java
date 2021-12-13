package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.UserPermission;
import com.deloop.user.data.db.models.query.QUserPermission;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserPermissionRepository implements IUserPermissionRepository {
    private final Database db;

    @Override
    public Optional<UserPermission> findBy(long id) {
        return Optional.ofNullable(new QUserPermission(db).id.eq(id).findOne());
    }

    @Override
    public Optional<UserPermission> findByName(String name) {
        return Optional.ofNullable(new QUserPermission(db).name.eq(name).findOne());
    }

    @Override
    public Optional<UserPermission> findByCode(String code) {
        return Optional.ofNullable(new QUserPermission(db).code.eq(code).findOne());
    }

    @Override
    public void save(UserPermission userPermission) {
        log.info("Saving new permission {} to db", userPermission.getName());
        db.save(userPermission);
//        return userPermission;
    }

    @Override
    public UserPermission update(UserPermission userPermission) {
        Optional<UserPermission> existing = findBy(userPermission.getId());
        if (existing.isEmpty()) {
            String message = "User permission with id " + userPermission.getId() + " does not exist!";
            log.info(message);
            throw new IllegalStateException(message);
        }
        db.update(userPermission);
        return userPermission;
    }

    @Override
    public boolean delete(long userPermissionId) {
        if (db.delete(UserPermission.builder().id(userPermissionId).build())) {
            return true;
        }
        String message = "User permission with id " + userPermissionId + " does not exist!";
        log.error(message);
        return false;
    }
}
