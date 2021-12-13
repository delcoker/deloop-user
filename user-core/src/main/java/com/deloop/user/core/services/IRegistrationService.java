package com.deloop.user.core.services;

import com.deloop.user.data.api.requests.RegistrationRequest;

public interface IRegistrationService {
    String register(RegistrationRequest registrationRequest);
}
