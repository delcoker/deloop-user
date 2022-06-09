package com.deloop.user.core.services.auth;

import com.deloop.user.core.models.requests.auth.RegistrationRequest;
import com.deloop.user.data.exceptions.EmailInvalidException;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import com.deloop.user.data.exceptions.InvalidConfirmationTokenException;

public interface RegistrationService {
    String register(RegistrationRequest registrationRequest) throws EmailInvalidException, EmailIsAlreadyTakenException;

    boolean confirmToken(String token) throws InvalidConfirmationTokenException;
}
