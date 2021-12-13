package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.UserDetail;

import java.util.Optional;

public interface IUserDetailRepository {

    Optional<UserDetail> findBy(long id);

//    Optional<UserDetail> findBy(String name);

    UserDetail save(UserDetail userDetail);

    UserDetail update(UserDetail userDetail);

    boolean delete(long UserDetailId);
}
