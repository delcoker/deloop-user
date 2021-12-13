package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.User;
import com.deloop.user.data.db.models.query.QUser;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
    private final Database db;

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(new QUser(db).id.eq(id).findOne());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(new QUser(db).email.eq(email).findOne());
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return Optional.ofNullable(new QUser(db).username.eq(userName).findOne());
    }

    @Override
    public void save(User user) {
        db.save(user);
//        return user;
    }

    @Override
    public User update(User user) {
        Optional<User> existingPerson = findById(user.getId());
        if (existingPerson.isEmpty()) {
            String message = "User with id " + user.getId() + " does not exist!";
            log.error(message);
            throw new IllegalStateException(message);
        }
        db.update(user);
        return user;
    }

    @Override
    public boolean delete(long userId) throws IllegalStateException {
        if (db.delete(User.builder().id(userId).build())) {
            return true;
        }
//        Optional<User> existingPerson = findById(userId);
//        if (existingPerson.isPresent()) {
        String message = "User with id " + userId + " does not exist!";
        log.error(message);
        return false;
//            throw new IllegalStateException(message);
////            throw new NotFoundException(message);
//        }
//        return db.delete(existingPerson);
    }

}
