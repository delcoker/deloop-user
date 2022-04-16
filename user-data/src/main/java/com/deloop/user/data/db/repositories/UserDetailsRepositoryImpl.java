package com.deloop.user.data.db.repositories;

import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.daos.UserDetailDao;
import com.deloop.user.data.db.enums.AddressType;
import com.deloop.user.data.db.enums.Gender;
import com.deloop.user.data.db.models.Address;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.db.models.UserDetail;
import com.deloop.user.data.db.models.query.QUser;
import com.deloop.user.data.db.models.query.QUserDetail;
import com.deloop.user.data.exceptions.NoSuchUserException;
import io.ebean.Database;
import io.ebean.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class UserDetailsRepositoryImpl implements UserDetailsRepository {
    private final Database db;

//    @Override
//    public Optional<UserDetail> findBy(long id) {
//        return Optional.ofNullable(new QUserDetail(db).id.eq(id).findOne());
//    }

    @Override
    public Optional<UserDetail> findBy(long userId) {
        return Optional.ofNullable(new QUserDetail(db).user.id.eq(userId).findOne());
    }

    @Override
    @Transactional
    public UserDetailDto save(UserDetailDao userDetailDao) throws NoSuchUserException {
        User user = new QUser(db).id.eq(userDetailDao.getUserId()).findOne();
        if (user == null) {
            throw new NoSuchUserException("Could not find user.");
        }

        UserDetail newUserDetails = UserDetail.builder()
                .profilePicture(userDetailDao.getProfilePicture())
                .firstName(userDetailDao.getFirstName())
                .otherNames(userDetailDao.getOtherNames())
                .lastName(userDetailDao.getLastName())
                .gender(Gender.getGenderFromText(userDetailDao.getGender()))
                .dateOfBirth(userDetailDao.getDateOfBirth())
                .placeOfBirth(userDetailDao.getPlaceOfBirth())
                .prefix(userDetailDao.getPrefix())
                .title(userDetailDao.getTitle())
                .memo(userDetailDao.getMemo())
//                .addresses(addresses)  // tries to save and crashes
                .lastLogin(userDetailDao.getLastLogin())
                .user(user)
                .build();

        Optional<UserDetail> optionalExistingUserDetail = findBy(user.getId());
        UserDetail existingUserDetail = null;
        if (optionalExistingUserDetail.isPresent()) {
            existingUserDetail = optionalExistingUserDetail.get();
            existingUserDetail.setProfilePicture(newUserDetails.getProfilePicture());
            existingUserDetail.setFirstName(newUserDetails.getFirstName());
            existingUserDetail.setOtherNames(newUserDetails.getOtherNames());
            existingUserDetail.setLastName(newUserDetails.getLastName());
            existingUserDetail.setGender(newUserDetails.getGender());
            existingUserDetail.setDateOfBirth(newUserDetails.getDateOfBirth());
            existingUserDetail.setPlaceOfBirth(newUserDetails.getPlaceOfBirth());
            existingUserDetail.setPrefix(newUserDetails.getPrefix());
            existingUserDetail.setTitle(newUserDetails.getTitle());
            existingUserDetail.setMemo(newUserDetails.getMemo());
            existingUserDetail.setLastLogin(newUserDetails.getLastLogin());
            db.save(existingUserDetail);
        } else {
            db.save(newUserDetails);
        }

//        find all types of addresses home

        List<Address> addresses = getAddresses(userDetailDao.getAddresses(), newUserDetails);
        // find list of addresses and map
        if (db.saveAll(addresses) < 0) {
            log.warn("Could not save some or all addresses");
        }

        if (existingUserDetail != null)
            return existingUserDetail.getUserDetailDto();
        else {
            return newUserDetails.getUserDetailDto();
        }
    }

    private List<Address> getAddresses(List<AddressDto> addresses, UserDetail userDetail) {
        return addresses.stream()
                .map(addressDto -> Address.builder()
                        .addressLine1(addressDto.getAddressLine1())
                        .addressLine2(addressDto.getAddressLine2())
                        .postCode(addressDto.getPostCode())
                        .addressType(AddressType.getAddressTypeFromText(addressDto.getAddressType()))
                        .country(addressDto.getCountry())
                        .state(addressDto.getState())
                        .city(addressDto.getCity())
//                        .userDetail(userDetail) // sometimes able to make association and save
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UserDetail update(UserDetail userDetail) {
        Optional<UserDetail> existing = findBy(userDetail.getId());
        if (existing.isEmpty()) {
            String message = "User detail with id " + userDetail.getId() + " does not exist!";
            log.info(message);
            throw new IllegalStateException(message);
        }
        db.update(userDetail);
        return userDetail;
    }

    @Override
    public boolean delete(long userDetailId) {
        if (db.delete(UserDetail.builder().id(userDetailId).build())) {
            return true;
        }
        String message = "User detail with id " + userDetailId + " does not exist!";
        log.error(message);
        return false;
    }
}
