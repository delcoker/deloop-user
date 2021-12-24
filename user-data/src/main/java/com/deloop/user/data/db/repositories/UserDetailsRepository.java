package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.UserDetail;
import com.deloop.user.data.db.models.query.QUserDetail;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserDetailsRepository implements IUserDetailsRepository {
    private final Database db;

    @Override
    public Optional<UserDetail> findBy(long id) {
        return Optional.ofNullable(new QUserDetail(db).id.eq(id).findOne());
    }

    @Override
    public UserDetail save(UserDetail userDetail) {
        db.save(userDetail);
        return userDetail;
    }

    @Override
    public UserDetail update(UserDetail userDetail) {
        Optional<UserDetail> existing = findBy(userDetail.getId());
        if (existing.isEmpty()) {
            String message = "User detail with id " + userDetail.getId() + " does not exist!";
            log.info(message);
            throw new IllegalStateException(message);
        }
        db.update(userDetail);
        return userDetail;
    }

    @Override
    public boolean delete(long userDetailId) {
        if (db.delete(UserDetail.builder().id(userDetailId).build())) {
            return true;
        }
        String message = "User detail with id " + userDetailId + " does not exist!";
        log.error(message);
        return false;
    }
}
