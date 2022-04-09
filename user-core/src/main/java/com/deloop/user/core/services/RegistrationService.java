package com.deloop.user.core.services;

import com.deloop.user.core.models.requests.RegistrationRequest;
import com.deloop.user.data.exceptions.EmailInvalidException;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;

public interface RegistrationService {
    String register(RegistrationRequest registrationRequest) throws EmailInvalidException, EmailIsAlreadyTakenException;

    String confirmToken(String token);
}
