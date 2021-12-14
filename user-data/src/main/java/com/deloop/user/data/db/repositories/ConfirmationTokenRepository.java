package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.ConfirmationToken;
import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.db.models.query.QLicenseType;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ConfirmationTokenRepository implements IConfirmationTokenRepository {
    private final Database db;

    @Override
    public Optional<ConfirmationToken> findByToken(String token) {
        return Optional.empty();
    }

    @Override
    public void save(ConfirmationToken confirmationToken) {
        db.save(confirmationToken);
    }

    @Override
    public int updateConfirmedAt(String token, LocalDateTime now) {
        return 0;
    }
}
