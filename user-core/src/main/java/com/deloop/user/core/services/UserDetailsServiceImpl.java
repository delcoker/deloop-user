package com.deloop.user.core.services;

import com.deloop.user.core.models.requests.AddUserDetailRequest;
import com.deloop.user.core.models.requests.GetUserDetailRequest;
import com.deloop.user.core.models.requests.UpdateUserDetailRequest;
import com.deloop.user.data.api.dtos.UserDetailDto;
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

    public void addUserDetails(AddUserDetailRequest addUserDetailRequest) {
        List<Address> addresses = getAddresses(addUserDetailRequest);

        UserDetail userDetail = UserDetail.builder()
                .id(addUserDetailRequest.getId())
                .profilePicture(addUserDetailRequest.getProfilePicture())
                .firstName(addUserDetailRequest.getFirstName())
                .otherNames(addUserDetailRequest.getOtherNames())
                .lastName(addUserDetailRequest.getLastName())
                .fullName(addUserDetailRequest.getFullName())
                .titledFullName(addUserDetailRequest.getTitledFullName())
                .shortName(addUserDetailRequest.getShortName())
                .initials(addUserDetailRequest.getInitials())
                .age(addUserDetailRequest.getAge())
                .gender(Gender.getGenderFromText(addUserDetailRequest.getGender()))
                .addresses(addresses)
                .dateOfBirth(addUserDetailRequest.getDateOfBirth())
                .placeOfBirth(addUserDetailRequest.getPlaceOfBirth())
                .prefix(addUserDetailRequest.getPrefix())
                .title(addUserDetailRequest.getTitle())
                .memo(addUserDetailRequest.getMemo())
                .user(User.builder().id(addUserDetailRequest.getUserId()).build())
                .build();
        userDetailsRepository.save(userDetail);
    }

    private List<Address> getAddresses(AddUserDetailRequest addUserDetailRequest) {
        return addUserDetailRequest.getAddresses().stream()
                .map(addressDto -> Address.builder()
                        .addressLine1(addressDto.getAddressLine1())
                        .addressLine2(addressDto.getAddressLine2())
                        .postCode(addressDto.getPostCode())
                        .addressType(AddressType.getAddressTypeFromText(addressDto.getAddressType()))
                        .country(addressDto.getCountry())
                        .state(addressDto.getState())
                        .city(addressDto.getCity())
                        .build())
                .collect(Collectors.toList());
    }

    public Optional<UserDetailDto> getUserDetails(GetUserDetailRequest getUserDetailRequest) {
        return userDetailsRepository.findBy(getUserDetailRequest.getUserId())
                .map(UserDetail::getUserDetailDto);
    }

    public void updateUserDetails(UpdateUserDetailRequest updateUserDetailRequest) {
        UserDetail userDetail = UserDetail.builder()
//                .id(updateUserDetailRequest.getId())
                .profilePicture(updateUserDetailRequest.getProfilePicture())
                .firstName(updateUserDetailRequest.getFirstName())
                .otherNames(updateUserDetailRequest.getOtherNames())
                .lastName(updateUserDetailRequest.getLastName())
                .fullName(updateUserDetailRequest.getFullName())
                .titledFullName(updateUserDetailRequest.getTitledFullName())
                .shortName(updateUserDetailRequest.getShortName())
                .initials(updateUserDetailRequest.getInitials())
                .age(updateUserDetailRequest.getAge())
                .gender(Gender.getGenderFromText(updateUserDetailRequest.getGender()))
//                .addresses(getAddresses(updateUserDetailRequest))  // fix this with mapper
                .dateOfBirth(updateUserDetailRequest.getDateOfBirth())
                .placeOfBirth(updateUserDetailRequest.getPlaceOfBirth())
                .prefix(updateUserDetailRequest.getPrefix())
                .title(updateUserDetailRequest.getTitle())
                .memo(updateUserDetailRequest.getMemo())
                .user(User.builder().id(updateUserDetailRequest.getUserId()).build())
                .build();
        userDetailsRepository.update(userDetail);
    }

    public void deleteUserDetails(AddUserDetailRequest addUserDetailRequest) {
        // TODO
    }
}