package com.example.api.controllers;


import com.deloop.user.core.services.LoginService;
import com.deloop.user.core.services.db.IUserService;
import com.deloop.user.data.api.requests.LoginRequest;
import com.deloop.user.data.api.responses.LoginResponse;
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
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse response = loginService.login(loginRequest);

        return ResponseEntity.ok(response);
    }


//    @GetMapping("/userinfo")
//    public ResponseEntity<?> getUserInfo(Principal user) {
//        UserDetails userDetails = userService.loadUserByUsername(user.getName());
//
//        UserDto userDto = (UserDto) userDetails;
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
