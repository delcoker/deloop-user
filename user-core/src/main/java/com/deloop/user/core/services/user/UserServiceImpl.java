package com.deloop.user.core.services.user;

import com.deloop.user.core.models.requests.AddUserRequest;
import com.deloop.user.core.models.requests.UserRequest;
import com.deloop.user.core.models.requests.auth.RegistrationRequest;
import com.deloop.user.core.services.IConfirmationTokenService;
import com.deloop.user.data.api.dtos.UserDto;
import com.deloop.user.data.db.enums.ConfirmationTokenType;
import com.deloop.user.data.db.models.ConfirmationToken;
import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.db.models.UserRole;
import com.deloop.user.data.db.repositories.UserRepository;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.ScreenNameNotFoundException;
import io.ebean.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IConfirmationTokenService confirmationTokenService;

    @Override
    public void addUser(AddUserRequest addUserRequest) {
        String encodedPassword = passwordEncoder.encode(addUserRequest.getPassword());
        User user = User.builder()
                .username(addUserRequest.getUsername())
                .email(addUserRequest.getEmail())
                .password(encodedPassword)
                .userRole(UserRole.builder().id(addUserRequest.getUserRole().getId()).build())
                .licenseType(addUserRequest.getLicenseType())
                .isVerified(addUserRequest.isVerified())
                .locked(false)
                .build();
        userRepository.save(user);
    }

    @Override
    @Transactional
    @org.springframework.transaction.annotation.Transactional
    public String signUpUser(RegistrationRequest registrationRequest) throws EmailIsAlreadyTakenException {
        Optional<User> optionalUser = userRepository.findByEmail(registrationRequest.getEmail());
        if (optionalUser.isPresent()) {
            // TODO: if user not verified send new confirmation token
            User existingUser = optionalUser.get();
            if (!existingUser.isVerified()) {
                return getConfirmationToken(existingUser);
            }
            throw new EmailIsAlreadyTakenException("Email already taken");
        }

        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(encodedPassword)
                .licenseType(LicenseType.builder().id(2).build())
                .userRole(UserRole.builder().id(3).build())
                .isVerified(false)
                .locked(false)
                .build();
        userRepository.save(user);

        // TODO: send confirmation token
        return getConfirmationToken(user);
    }

    private String getConfirmationToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .type(ConfirmationTokenType.EMAIL)
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
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
    public UserDto loadUserByEmail(String email) throws EmailNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(String.format("Email not found %s", email)))
                .getUserDto();
    }

    @Override // spring method
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return loadUserByEmail(email);
        } catch (EmailNotFoundException e) {
            throw new UsernameNotFoundException("Email or username not found.");
        }
    }

    @Override
    public int verifyUser(String email) {
        return userRepository.verifyUser(email);
    }


//    @Override
//    public String login(LoginRequest loginRequest) throws EmailNotFoundException {
//        User user = loadUserByEmail(UserRequest.builder().email(loginRequest.getEmail()).build());
//        return "jwtToken";
//    }
}