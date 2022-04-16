package com.deloop.user.data.db.repositories;

import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.daos.UserDetailDao;
import com.deloop.user.data.db.models.UserDetail;
import com.deloop.user.data.exceptions.NoSuchUserException;

import java.util.Optional;

public interface UserDetailsRepository {

    Optional<UserDetail> findBy(long userId);

//    Optional<UserDetail> findBy(String name);

    UserDetailDto save(UserDetailDao userDetailDao) throws NoSuchUserException;

    UserDetail update(UserDetail userDetail);

    boolean delete(long UserDetailId);
}
