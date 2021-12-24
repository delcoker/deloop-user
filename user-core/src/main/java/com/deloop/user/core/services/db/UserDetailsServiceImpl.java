package com.deloop.user.core.services.db;

import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.api.requests.UserDetailRequest;
import com.deloop.user.data.db.enums.AddressType;
import com.deloop.user.data.db.enums.Gender;
import com.deloop.user.data.db.models.Address;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.db.models.UserDetail;
import com.deloop.user.data.db.repositories.IUserDetailsRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements IUserDetailsService {
    public final IUserDetailsRepository userDetailsRepository;

    public void addUserDetails(UserDetailRequest userDetailRequest) {
        List<Address> addresses = getAddresses(userDetailRequest);

        UserDetail userDetail = UserDetail.builder()
                .id(userDetailRequest.getId())
                .profilePicture(userDetailRequest.getProfilePicture())
                .firstName(userDetailRequest.getFirstName())
                .otherNames(userDetailRequest.getOtherNames())
                .lastName(userDetailRequest.getLastName())
                .fullName(userDetailRequest.getFullName())
                .titledFullName(userDetailRequest.getTitledFullName())
                .shortName(userDetailRequest.getShortName())
                .initials(userDetailRequest.getInitials())
                .age(userDetailRequest.getAge())
                .gender(Gender.getGenderFromText(userDetailRequest.getGender()))
                .addresses(addresses)
                .dateOfBirth(userDetailRequest.getDateOfBirth())
                .placeOfBirth(userDetailRequest.getPlaceOfBirth())
                .prefix(userDetailRequest.getPrefix())
                .title(userDetailRequest.getTitle())
                .memo(userDetailRequest.getMemo())
                .user(User.builder().id(userDetailRequest.getUserId()).build())
                .build();
        userDetailsRepository.save(userDetail);
    }

    private List<Address> getAddresses(UserDetailRequest userDetailRequest) {
        return userDetailRequest.getAddresses().stream()
                .map(addressDto -> Address.builder().addressLine1(addressDto.getAddressLine1())
                        .addressLine2(addressDto.getAddressLine2())
                        .postCode(addressDto.getPostCode())
                        .addressType(AddressType.getAddressTypeFromText(addressDto.getAddressType()))
                        .country(addressDto.getCountry())
                        .state(addressDto.getState())
                        .city(addressDto.getCity())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<UserDetailDto> getUserDetails(UserDetailRequest userDetailRequest) {
        return userDetailsRepository.findBy(userDetailRequest.getId())
                .map(UserDetail::getUserDetailDto);
    }

    public void updateUserDetails(UserDetailRequest userDetailRequest) {
        UserDetail userDetail = UserDetail.builder()
                .id(userDetailRequest.getId())
                .profilePicture(userDetailRequest.getProfilePicture())
                .firstName(userDetailRequest.getFirstName())
                .otherNames(userDetailRequest.getOtherNames())
                .lastName(userDetailRequest.getLastName())
                .fullName(userDetailRequest.getFullName())
                .titledFullName(userDetailRequest.getTitledFullName())
                .shortName(userDetailRequest.getShortName())
                .initials(userDetailRequest.getInitials())
                .age(userDetailRequest.getAge())
                .gender(Gender.getGenderFromText(userDetailRequest.getGender()))
                .addresses(getAddresses(userDetailRequest))
                .dateOfBirth(userDetailRequest.getDateOfBirth())
                .placeOfBirth(userDetailRequest.getPlaceOfBirth())
                .prefix(userDetailRequest.getPrefix())
                .title(userDetailRequest.getTitle())
                .memo(userDetailRequest.getMemo())
                .user(User.builder().id(userDetailRequest.getUserId()).build())
                .build();
        userDetailsRepository.update(userDetail);
    }

    public void deleteUserDetails(UserDetailRequest userDetailRequest) {
        // TODO
    }
}