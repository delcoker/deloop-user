package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.Address;
import com.deloop.user.data.db.models.query.QAddress;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class AddressRepository implements IAddressRepository {
    private final Database db;

    @Override
    public Optional<Address> findById(long id) {
        return Optional.ofNullable(new QAddress(db).id.eq(id).findOne());
    }

    @Override
    public Optional<Address> findByPostCode(String postCode) {
        return Optional.ofNullable(new QAddress(db).postCode.eq(postCode).findOne());
    }

    @Override
    public Address save(Address address) {
        db.save(address);
        return address;
    }

    @Override
    public Address update(Address address) {
        Optional<Address> existing = findById(address.getId());
        if (existing.isEmpty()) {
            String message = "Address with id " + address.getId() + " does not exist!";
            log.info(message);
            throw new IllegalStateException(message);
        }
        db.update(address);
        return address;
    }

    @Override
    public boolean delete(long addressId) {
        if (db.delete(Address.builder().id(addressId).build())) {
            return true;
        }
        String message = "Address with id " + addressId + " does not exist!";
        log.error(message);
        return false;
    }
}
