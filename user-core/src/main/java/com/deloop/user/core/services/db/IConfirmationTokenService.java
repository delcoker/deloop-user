package com.deloop.user.core.services.db;

import com.deloop.user.data.db.models.ConfirmationToken;

import java.util.Optional;

public interface IConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    int setConfirmedAt(String token);
}