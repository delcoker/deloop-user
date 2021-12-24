package com.deloop.user.core.services.db;

import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.api.requests.UserDetailRequest;

import java.util.Optional;

public interface IUserDetailsService {
    void addUserDetails(UserDetailRequest userDetailRequest);

    Optional<UserDetailDto> getUserDetails(UserDetailRequest userDetailRequest);

    void updateUserDetails(UserDetailRequest userDetailRequest);

    void deleteUserDetails(UserDetailRequest userDetailRequest);
}