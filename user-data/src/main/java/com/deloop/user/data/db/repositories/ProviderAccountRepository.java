package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.ProviderAccount;
import com.deloop.user.data.db.models.query.QProviderAccount;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ProviderAccountRepository implements IProviderAccountRepository {
    private final Database db;

    @Override
    public Optional<ProviderAccount> findBy(long id) {
        return Optional.ofNullable(new QProviderAccount(db).id.eq(id).findOne());
    }

    @Override
    public Optional<ProviderAccount> findBy(String name) {
        return Optional.ofNullable(new QProviderAccount(db).provider.eq(name).findOne());
    }

    @Override
    public ProviderAccount save(ProviderAccount providerAccount) {
        db.save(providerAccount);
        return providerAccount;
    }

    @Override
    public ProviderAccount update(ProviderAccount providerAccount) {
        Optional<ProviderAccount> existing = findBy(providerAccount.getId());
        if (existing.isPresent()) {
            String message = "Provider with id " + providerAccount.getId() + " does not exist!";
            log.info(message);
            throw new IllegalStateException(message);
        }
        db.update(providerAccount);
        return providerAccount;
    }

    @Override
    public boolean delete(long providerAccountId) {
        if (db.delete(ProviderAccount.builder().id(providerAccountId).build())) {
            return true;
        }
        String message = "Provider with id " + providerAccountId + " does not exist!";
        log.error(message);
        return false;
    }
}
