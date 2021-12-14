package com.deloop.user.core.services.email;

public interface EmailSenderService {
    void send(String emailAddressTo, String composedEmail);
}