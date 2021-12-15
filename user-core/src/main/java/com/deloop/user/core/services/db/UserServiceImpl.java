package com.deloop.user.core.services.db;

import com.deloop.user.data.api.requests.RegistrationRequest;
import com.deloop.user.data.api.requests.UserRequest;
import com.deloop.user.data.db.enums.ConfirmationTokenType;
import com.deloop.user.data.db.models.ConfirmationToken;
import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.db.models.UserRole;
import com.deloop.user.data.db.repositories.IUserRepository;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.ScreenNameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IConfirmationTokenService confirmationTokenService;

    @Override
    public void addUser(UserRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());
        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(encodedPassword)
                .userRole(UserRole.builder().id(userRequest.getUserRole().getId()).build())
                .licenseType(userRequest.getLicenseType())
                .isVerified(userRequest.isVerified())
                .locked(false)
                .build();
        userRepository.save(user);
    }

    @Override
    public String signUpUser(RegistrationRequest registrationRequest) throws EmailIsAlreadyTakenException {
        Optional<User> optionalUser = userRepository.findByEmail(registrationRequest.getEmail());
        if (optionalUser.isPresent()) {
            // TODO: if user not verified send new confirmation token
            User existingUser = optionalUser.get();
            if (!existingUser.isVerified()) {
                return sendConfirmationToken(existingUser);
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
        return sendConfirmationToken(user);
    }

    private String sendConfirmationToken(User user) {
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
    public User loadUserByEmail(UserRequest userRequest) throws EmailNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new EmailNotFoundException("Email not found");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Email or username not found.");
        }

        return optionalUser.get().getUserDto();
    }

    @Override
    public int verifyUser(String email) {
        return userRepository.verifyUser(email);
    }
}