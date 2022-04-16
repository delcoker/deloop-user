package com.deloop.user.core.services.user;

import com.deloop.user.core.models.requests.AddUserDetailRequest;
import com.deloop.user.core.models.requests.GetUserDetailRequest;
import com.deloop.user.core.models.requests.UpdateUserDetailRequest;
import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.exceptions.NoSuchUserException;

import java.util.Optional;

public interface IUserDetailsService {
    boolean addUserDetails(AddUserDetailRequest addUserDetailRequest) throws NoSuchUserException;

    Optional<UserDetailDto> getUserDetails(GetUserDetailRequest getUserDetailRequest);

    void updateUserDetails(UpdateUserDetailRequest updateUserDetailRequest);

    void deleteUserDetails(AddUserDetailRequest addUserDetailRequest);
}