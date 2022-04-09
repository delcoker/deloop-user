package com.deloop.user.api.controllers;


import com.deloop.user.core.models.requests.AddUserDetailRequest;
import com.deloop.user.core.services.IUserDetailsService;
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
    public ResponseEntity<?> addDetails(@RequestBody AddUserDetailRequest addUserDetailRequest) {
//
        userDetailsService.addUserDetails(addUserDetailRequest);
//        UserDetailsResponse response = userDetailsService.addUserDetails(addUserDetailRequest);

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
