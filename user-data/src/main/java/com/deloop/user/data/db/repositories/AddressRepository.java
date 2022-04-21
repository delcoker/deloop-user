package com.deloop.user.data.db.repositories;

import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.daos.AddressDao;
import com.deloop.user.data.db.models.Address;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing the lifecycle of {@link Address} entities.
 */
public interface AddressRepository {

    boolean delete(long addressId);

    /**
     * Finds an Address based on user detail ID.
     *
     * @param id ID
     * @return a {@link Address}
     */
    List<Address> findByUserDetailId(long id);

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
     * @param address      a {@link AddressDao}
     * @param userDetailId
     * @return the stored {@link AddressDto}
     */
    AddressDto save(AddressDao address, long userDetailId);

    /**
     * Stores the given Address.
     *
     * @param addressDao a {@link AddressDao}
     * @return the stored {@link AddressDto}
     */
    AddressDto update(AddressDao addressDao);
}
