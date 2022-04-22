package com.deloop.user.data.db.repositories;

import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.daos.AddressDao;
import com.deloop.user.data.daos.UserDetailDao;
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

    private final AddressRepository addressRepository;

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
                .gender(userDetailDao.getGender())
                .dateOfBirth(userDetailDao.getDateOfBirth())
                .placeOfBirth(userDetailDao.getPlaceOfBirth())
                .prefix(userDetailDao.getPrefix())
                .title(userDetailDao.getTitle())
                .memo(userDetailDao.getMemo())
//                .addresses(map(userDetailDao.getAddresses(), null)) // TODO does not bind userDetailId if new entry
                .lastLogin(userDetailDao.getLastLogin())
                .user(user)
                .build();

        Optional<UserDetail> optionalExistingUserDetail = findBy(user.getId());
        if (optionalExistingUserDetail.isPresent()) {

            UserDetail existingUserDetail = optionalExistingUserDetail.get().toBuilder()
                    .profilePicture(newUserDetails.getProfilePicture())
                    .firstName(newUserDetails.getFirstName())
                    .lastName(newUserDetails.getLastName())
                    .otherNames(newUserDetails.getOtherNames())
                    .gender(newUserDetails.getGender())
                    .dateOfBirth(newUserDetails.getDateOfBirth())
                    .placeOfBirth(newUserDetails.getPlaceOfBirth())
                    .prefix(newUserDetails.getPrefix())
                    .title(newUserDetails.getTitle())
                    .memo(newUserDetails.getMemo())
                    .lastLogin(newUserDetails.getLastLogin())
                    .build();

            db.update(existingUserDetail);

            // TODO: update addresses will be in it's own service/repo

//            long newUserDetailsId = existingUserDetail.getId();
//            List<Address> existingAddresses = addressRepository.findByUserDetailId(newUserDetailsId);
//            existingUserDetail.setAddresses(existingAddresses);

            UserDetail userDetailto = new QUserDetail(db).id.eq(existingUserDetail.getId()).findOne();
            UserDetailDto u = userDetailto.getUserDetailDto();
            return u;

        }
        db.save(newUserDetails);

        List<Address> addresses = map(userDetailDao.getAddresses(), newUserDetails);

        if (db.saveAll(addresses) < 0) {
            log.warn("Could not save some or all addresses");
        }

        UserDetailDto u = new QUserDetail(db).user.id.eq(user.getId()).findOne().getUserDetailDto();
        return u;
    }

    private Address map(AddressDao addressDao, UserDetail userDetail) {
        return Address.builder()
                .id(addressDao.getId())
                .addressType(addressDao.getAddressType())
                .addressLine1(addressDao.getAddressLine1())
                .addressLine2(addressDao.getAddressLine2())
                .city(addressDao.getCity())
                .state(addressDao.getState())
                .postCode(addressDao.getPostCode())
                .country(addressDao.getCountry())
                .userDetail(userDetail)
                .build();
    }

    private List<Address> map(List<AddressDao> addresses, UserDetail userDetail) {
        return addresses.stream()
                .map(addressDto -> map(addressDto, userDetail))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetail update(UserDetail userDetail) {
        Optional<UserDetail> existing = findBy(userDetail.getId());
        if (existing.isPresent()) {
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
