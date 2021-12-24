package com.example.api.controllers;


import com.deloop.user.core.services.db.IUserDetailsService;
import com.deloop.user.data.api.requests.UserDetailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDetailsController {

    private final IUserDetailsService userDetailsService;

    @PostMapping("/adduserdetails")
    public ResponseEntity<?> addDetails(@RequestBody UserDetailRequest userDetailRequest) {

        userDetailsService.addUserDetails(userDetailRequest);
//        UserDetailsResponse response = userDetailsService.addUserDetails(userDetailRequest);

        return ResponseEntity.ok("response");
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
