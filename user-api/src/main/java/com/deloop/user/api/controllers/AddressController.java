package com.deloop.user.api.controllers;


import com.deloop.user.core.models.requests.address.AddAddressRequest;
import com.deloop.user.core.models.requests.address.UpdateAddressRequest;
import com.deloop.user.core.services.AddressService;
import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.exceptions.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    //    private final IUserDetailsService userDetailsService;
    private final AddressService addressService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> add(@SpringQueryMap AddAddressRequest addAddressRequest) throws EmailNotFoundException {

        return ResponseEntity.ok(addressService.add(addAddressRequest));
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> update(@SpringQueryMap UpdateAddressRequest updateAddressRequest) {
//
        return ResponseEntity.ok(addressService.update(updateAddressRequest));
    }
//    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserDto> getUserInfo(Principal principal) throws EmailNotFoundException {
//        User user = userService.loadUserByEmail(UserRequest.builder().email(principal.getName()).build());
//
//        UserDto userDto = user.getUserDto();
//
////        UserInfo userInfo=new UserInfo();
////        userInfo.setFirstName(userObj.getFirstName());
////        userInfo.setLastName(userObj.getLastName());
////        userInfo.setRoles(userObj.getAuthorities().toArray());
//
//
//        return ResponseEntity.ok(userDto);
//    }
}
