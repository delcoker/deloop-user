package com.deloop.user.core.services.user;

import com.deloop.user.core.models.requests.AddOrUpdateUserDetailRequest;
import com.deloop.user.core.models.requests.GetUserDetailRequest;
import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.NoSuchUserException;

import java.util.Optional;

public interface IUserDetailsService {
    UserDetailDto addUserDetails(AddOrUpdateUserDetailRequest addOrUpdateUserDetailRequest) throws NoSuchUserException, EmailNotFoundException;

    Optional<UserDetailDto> getUserDetails(GetUserDetailRequest getUserDetailRequest);

//    void updateUserDetails(UpdateUserDetailRequest updateUserDetailRequest);

//    void deleteUserDetails(AddOrUpdateUserDetailRequest addOrUpdateUserDetailRequest);
}