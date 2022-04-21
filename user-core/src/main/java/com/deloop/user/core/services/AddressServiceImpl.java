package com.deloop.user.core.services;


import com.deloop.user.core.models.requests.address.AddAddressRequest;
import com.deloop.user.core.models.requests.address.UpdateAddressRequest;
import com.deloop.user.core.models.requests.userdetails.GetUserDetailRequest;
import com.deloop.user.core.services.auth.AuthenticationFacade;
import com.deloop.user.core.services.user.IUserDetailsService;
import com.deloop.user.core.services.user.UserService;
import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.daos.AddressDao;
import com.deloop.user.data.db.repositories.AddressRepository;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AuthenticationFacade authenticationFacade;
    private final AddressRepository addressRepository;
    private final UserService userService;
    private final IUserDetailsService userDetailsService;

    @Override
    public AddressDto add(AddAddressRequest addAddressRequest) throws EmailNotFoundException {
        Authentication authentication = authenticationFacade.getAuthentication();
        long userId = userService.loadUserByEmail(authentication.getName()).getId();

        if (userId <= 0) {
            throw new EmailNotFoundException("User should exist");
        }

        long userDetailId = userDetailsService.getUserDetails(GetUserDetailRequest.builder().userId(userId).build())
                .orElseThrow(() -> new EmailNotFoundException(""))
                .getId();

        if (userDetailId <= 0) {
            throw new EmailNotFoundException("User should exist");
        }

        AddressDao addressDao = AddressDao.builder()
                .addressLine1(addAddressRequest.getAddressLine1())
                .addressLine2(addAddressRequest.getAddressLine2())
                .addressType(addAddressRequest.getAddressType())
                .city(addAddressRequest.getCity())
                .country(addAddressRequest.getCountry())
                .state(addAddressRequest.getState())
                .postCode(addAddressRequest.getPostCode())
                .build();

        return addressRepository.save(addressDao, userDetailId);
    }

    public AddressDto update(UpdateAddressRequest updateAddressRequest) {
        AddressDao addressDao = AddressDao.builder()
                .addressLine1(updateAddressRequest.getAddressLine1())
                .addressLine2(updateAddressRequest.getAddressLine2())
                .addressType(updateAddressRequest.getAddressType())
                .city(updateAddressRequest.getCity())
                .country(updateAddressRequest.getCountry())
                .state(updateAddressRequest.getState())
                .postCode(updateAddressRequest.getPostCode())
                .id(updateAddressRequest.getId())
                .build();

        return addressRepository.update(addressDao);
    }
}
