package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.Address;

import java.util.Optional;

/**
 * Repository for managing the lifecycle of {@link Address} entities.
 */
public interface IAddressRepository {

    boolean delete(long addressId);

    /**
     * Finds an Address based on its ID.
     *
     * @param id ID
     * @return a {@link Address}
     */
    Optional<Address> findById(long id);

    /**
     * Finds an Address by email address.
     *
     * @param postCode Post Code
     * @return an {@link Address}
     */
    Optional<Address> findByPostCode(String postCode);

    /**
     * Stores the given Address.
     *
     * @param Address a {@link Address}
     * @return the stored {@link Address}
     */
    Address save(Address Address);

    /**
     * Stores the given Address.
     *
     * @param Address a {@link Address}
     * @return the stored {@link Address}
     */
    Address update(Address Address);
}
