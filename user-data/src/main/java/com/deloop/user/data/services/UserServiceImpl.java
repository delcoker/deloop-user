package com.deloop.user.data.services;

import com.deloop.user.data.api.requests.UserRequest;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.db.repositories.IUserRepository;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.ScreenNameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements IUserService/*, UserDetailsService **/ {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(encodedPassword)
                .isVerified(false)
                .locked(false)
                .build();
        userRepository.save(user);
    }

    @Override
    public User loadUserByScreenName(UserRequest userRequest) throws ScreenNameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUserName(userRequest.getEmail());
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new ScreenNameNotFoundException("Screen name not found");
    }

    @Override
    public User loadUserByEmail(UserRequest userRequest) throws EmailNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new EmailNotFoundException("Email not found");
    }


//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return null;
//    }
}