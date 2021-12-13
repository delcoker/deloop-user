package com.deloop.user.core.services;

import com.deloop.user.data.db.models.User;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.ScreenNameNotFoundException;
import com.deloop.user.data.api.requests.UserRequest;

public interface IUserService {
    void addUser(UserRequest userRequest);

    User loadUserByScreenName(UserRequest userRequest) throws ScreenNameNotFoundException;

    User loadUserByEmail(UserRequest request) throws EmailNotFoundException;
}