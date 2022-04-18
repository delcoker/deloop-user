package com.deloop.user.core.services.auth;

import com.deloop.user.core.models.requests.auth.RegistrationRequest;
import com.deloop.user.data.exceptions.EmailInvalidException;
import com.deloop.user.data.exceptions.EmailIsAlreadyTakenException;
import org.springframework.web.servlet.view.RedirectView;

public interface RegistrationService {
    String register(RegistrationRequest registrationRequest) throws EmailInvalidException, EmailIsAlreadyTakenException;

    RedirectView confirmToken(String token);
}
