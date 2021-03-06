package com.deloop.user.core.services.user;

import com.deloop.user.core.models.requests.AddUserRequest;
import com.deloop.user.core.models.requests.UserRequest;
import com.deloop.user.data.api.dtos.UserDto;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.ScreenNameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    void addUser(AddUserRequest addUserRequest);

    String signUpUser(String email, String username, String password) throws EmailIsAlreadyTakenException;

    User loadUserByScreenName(UserRequest userRequest) throws ScreenNameNotFoundException;

    UserDto loadUserByEmail(String email) throws EmailNotFoundException;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    boolean verifyUser(String email);
}