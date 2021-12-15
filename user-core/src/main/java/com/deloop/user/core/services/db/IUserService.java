package com.deloop.user.core.services.db;

import com.deloop.user.data.api.requests.LoginRequest;
import com.deloop.user.data.api.requests.RegistrationRequest;
import com.deloop.user.data.api.requests.UserRequest;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.ScreenNameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {
    void addUser(UserRequest userRequest);

    String signUpUser(RegistrationRequest registrationRequest) throws EmailIsAlreadyTakenException;

    User loadUserByScreenName(UserRequest userRequest) throws ScreenNameNotFoundException;

    User loadUserByEmail(UserRequest request) throws EmailNotFoundException;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    int verifyUser(String email);

    String login(LoginRequest loginRequest) throws EmailNotFoundException;
}