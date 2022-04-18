package com.deloop.user.core.services.user;

import com.deloop.user.core.models.requests.userdetails.AddOrUpdateUserDetailRequest;
import com.deloop.user.core.models.requests.userdetails.GetUserDetailRequest;
import com.deloop.user.core.services.auth.AuthenticationFacade;
import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.daos.AddressDao;
import com.deloop.user.data.daos.UserDetailDao;
import com.deloop.user.data.db.models.UserDetail;
import com.deloop.user.data.db.repositories.UserDetailsRepository;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import com.deloop.user.data.exceptions.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements IUserDetailsService {
    public final UserDetailsRepository userDetailsRepository;
    public final AuthenticationFacade authenticationFacade;
    public final UserService userService;

    public UserDetailDto addUserDetails(AddOrUpdateUserDetailRequest addOrUpdateUserDetailRequest) throws NoSuchUserException, EmailNotFoundException {

        Authentication authentication = authenticationFacade.getAuthentication();
        long userId = addOrUpdateUserDetailRequest.getUserId() <= 0
                ? userService.loadUserByEmail(authentication.getName()).getId()
                : addOrUpdateUserDetailRequest.getUserId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        UserDetailDao userDetailDao = UserDetailDao.builder()
                .profilePicture(addOrUpdateUserDetailRequest.getProfilePicture())
                .firstName(addOrUpdateUserDetailRequest.getFirstName())
                .otherNames(addOrUpdateUserDetailRequest.getOtherNames())
                .lastName(addOrUpdateUserDetailRequest.getLastName())
                .gender(addOrUpdateUserDetailRequest.getGender())
//                .addresses(map(Collections.singletonList(addOrUpdateUserDetailRequest.getAddress())))
//                .dateOfBirth(LocalDateTime.parse(addOrUpdateUserDetailRequest.getDateOfBirth() + " 00:00", formatter))
                .dateOfBirth(addOrUpdateUserDetailRequest.getDateOfBirth())
                .placeOfBirth(addOrUpdateUserDetailRequest.getPlaceOfBirth())
                .prefix(addOrUpdateUserDetailRequest.getPrefix())
                .title(addOrUpdateUserDetailRequest.getTitle())
                .memo(addOrUpdateUserDetailRequest.getMemo())
                .userId(userId)
                .build();

        return userDetailsRepository.save(userDetailDao);
    }

    private AddressDao map(AddressDto addressDto) {
        return AddressDao.builder()
                .addressLine1(addressDto.getAddressLine1())
                .addressLine2(addressDto.getAddressLine2())
                .postCode(addressDto.getPostCode())
                .addressType(addressDto.getAddressType())
                .country(addressDto.getCountry())
                .state(addressDto.getState())
                .city(addressDto.getCity())
                .build();
    }

    private List<AddressDao> map(List<AddressDto> addresses) {
        return addresses.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Optional<UserDetailDto> getUserDetails(GetUserDetailRequest getUserDetailRequest) {
        return userDetailsRepository.findBy(getUserDetailRequest.getUserId())
                .map(UserDetail::getUserDetailDto);
    }

//    public void updateUserDetails(UpdateUserDetailRequest updateUserDetailRequest) {
//        UserDetail userDetail = UserDetail.builder()
//                .profilePicture(updateUserDetailRequest.getProfilePicture())
//                .firstName(updateUserDetailRequest.getFirstName())
//                .otherNames(updateUserDetailRequest.getOtherNames())
//                .lastName(updateUserDetailRequest.getLastName())
//                .age(updateUserDetailRequest.getAge())
//                .gender(Gender.getGenderFromText(updateUserDetailRequest.getGender()))
////                .addresses(getAddresses(updateUserDetailRequest))  // maybe use a mapper
//                .dateOfBirth(updateUserDetailRequest.getDateOfBirth())
//                .placeOfBirth(updateUserDetailRequest.getPlaceOfBirth())
//                .prefix(updateUserDetailRequest.getPrefix())
//                .title(updateUserDetailRequest.getTitle())
//                .memo(updateUserDetailRequest.getMemo())
//                .user(User.builder().id(updateUserDetailRequest.getUserId()).build())
//                .build();
//        userDetailsRepository.update(userDetail);
//    }

//    public void deleteUserDetails(AddOrUpdateUserDetailRequest addOrUpdateUserDetailRequest) {
//        // TODO
//    }
}