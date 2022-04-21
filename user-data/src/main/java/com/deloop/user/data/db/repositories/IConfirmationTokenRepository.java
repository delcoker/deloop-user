package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.ConfirmationToken;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.exceptions.NoSuchUserException;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository for managing the lifecycle of {@link com.deloop.user.data.db.models.ConfirmationToken} entities.
 */
public interface IConfirmationTokenRepository {

    Optional<ConfirmationToken> findByToken(String token);

    void save(ConfirmationToken token);

    int updateConfirmedAt(String token, LocalDateTime now);
}
