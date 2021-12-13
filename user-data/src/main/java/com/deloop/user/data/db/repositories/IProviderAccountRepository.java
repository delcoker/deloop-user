package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.ProviderAccount;

import java.util.Optional;

public interface IProviderAccountRepository {

    Optional<ProviderAccount> findBy(long id);

    Optional<ProviderAccount> findBy(String name);

    ProviderAccount save(ProviderAccount providerAccount);

    ProviderAccount update(ProviderAccount providerAccount);

    boolean delete(long id);
}
