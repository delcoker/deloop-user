package com.deloop.user.core.services;

import com.deloop.user.data.api.requests.RegistrationRequest;
import com.deloop.user.data.exceptions.EmailInvalidException;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final IUserService userService;
    private final EmailValidatorService emailValidatorServiceImpl;

    @Override
    public String register(RegistrationRequest registrationRequest) throws EmailInvalidException, EmailIsAlreadyTakenException {
        boolean isValid = emailValidatorServiceImpl.test(registrationRequest.getEmail());
        if (!isValid) {
            throw new EmailInvalidException("Invalid email");
        }
        return userService.signUpUser(registrationRequest);
    }


}
