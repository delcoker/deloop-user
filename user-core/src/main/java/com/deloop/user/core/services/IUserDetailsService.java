package com.deloop.user.core.services;

import com.deloop.user.core.models.requests.AddUserDetailRequest;
import com.deloop.user.core.models.requests.GetUserDetailRequest;
import com.deloop.user.core.models.requests.UpdateUserDetailRequest;
import com.deloop.user.data.api.dtos.UserDetailDto;

import java.util.Optional;

public interface IUserDetailsService {
    void addUserDetails(AddUserDetailRequest addUserDetailRequest);

    Optional<UserDetailDto> getUserDetails(GetUserDetailRequest getUserDetailRequest);

    void updateUserDetails(UpdateUserDetailRequest updateUserDetailRequest);

    void deleteUserDetails(AddUserDetailRequest addUserDetailRequest);
}