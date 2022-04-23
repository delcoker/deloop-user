package com.deloop.user.data.db.repositories;

import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.daos.AddressDao;
import com.deloop.user.data.db.models.Address;
import com.deloop.user.data.db.models.UserDetail;
import com.deloop.user.data.db.models.query.QAddress;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {
    private final Database db;

    @Override
    public List<Address> findByUserDetailId(long id) {
        return new QAddress(db).userDetail.id.eq(id).findList();
    }

    @Override
    public Optional<Address> findById(long id) {
        return Optional.ofNullable(new QAddress(db).id.eq(id).findOne());
    }

    @Override
    public Optional<Address> findByPostCode(String postCode) {
        return Optional.ofNullable(new QAddress(db).postCode.eq(postCode).findOne());
    }

    @Override
    public AddressDto save(AddressDao addressDao, long userDetailId) {
        Address address = Address.builder()
                .addressType(addressDao.getAddressType())
                .addressLine1(addressDao.getAddressLine1())
                .addressLine2(addressDao.getAddressLine2())
                .city(addressDao.getCity())
                .state(addressDao.getState())
                .postCode(addressDao.getPostCode())
                .country(addressDao.getCountry())
                .userDetail(UserDetail.builder().id(userDetailId).build())
                .build();

        db.save(address);
        return map(address);
    }

    @Override
    public AddressDto update(AddressDao addressDao) {
        Optional<Address> existing1 = findById(addressDao.getId());

        if (!existing1.isPresent()) {

            String message = "Address with id " + addressDao.getId() + " does not exist!";
            log.info(message);
            throw new IllegalStateException(message);
        }
        Address existing = existing1.get();

        Address updatedAddress = existing.toBuilder()
                .id(addressDao.getId())
                .addressType(addressDao.getAddressType())
                .addressLine1(addressDao.getAddressLine1())
                .addressLine2(addressDao.getAddressLine2())
                .city(addressDao.getCity())
                .state(addressDao.getState())
                .postCode(addressDao.getPostCode())
                .country(addressDao.getCountry())
                .build();

        db.update(updatedAddress);
        return map(updatedAddress);
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

    private AddressDto map(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .addressType(address.getAddressType())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .postCode(address.getPostCode())
                .country(address.getCountry())
                .build();
    }

//    private Address mapAddress(AddressDao addressDao) {
//        return Address.builder()
//                .id(addressDao.getId())
//                .addressType(addressDao.getAddressType())
//                .addressLine1(addressDao.getAddressLine1())
//                .addressLine2(addressDao.getAddressLine2())
//                .city(addressDao.getCity())
//                .state(addressDao.getState())
//                .postCode(addressDao.getPostCode())
//                .country(addressDao.getCountry())
//                .build();
//    }
}
