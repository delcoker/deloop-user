package com.deloop.user.data.db.repositories;

import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserTypeRepository implements IUserTypeRepository {
    private final Database db;

//    @Override
//    public Optional<UserType> findBy(long id) {
//        return Optional.ofNullable(new QUserType(db).id.eq(id).findOne());
//    }
//
//    @Override
//    public Optional<UserType> findByName(String name) {
//        return Optional.ofNullable(new QUserType(db).name.eq(name).findOne());
//    }
//
//    @Override
//    public UserType save(UserType userType) {
//        db.save(userType);
//        return userType;
//    }
//
//    @Override
//    public UserType update(UserType userType) {
//        Optional<UserType> existing = findBy(userType.getId());
//        if (existing.isEmpty()) {
//            String message = "User type with id " + userType.getId() + " does not exist!";
//            log.info(message);
//            throw new IllegalStateException(message);
//        }
//        db.update(userType);
//        return userType;
//    }
//
//    @Override
//    public boolean delete(long userType) {
//        if (db.delete(UserType.builder().id(userType).build())) {
//            return true;
//        }
//        String message = "User type with id " + userType + " does not exist!";
//        log.error(message);
//        return false;
//    }
}
