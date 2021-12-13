package com.deloop.user.data.services;

import com.deloop.user.data.api.requests.RegistrationRequest;

public interface IRegistrationService {
    String register(RegistrationRequest registrationRequest);
}
