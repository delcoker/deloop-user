package com.deloop.user.data.aa_delete_this;//package com.deloop.user.data.aa_delete_this;
//
//import com.deloop.user.data.api.requests.UserRequest;
//import com.deloop.user.core.services.IUserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class SessionController {
//    private final IUserService userService;
//
//    @RequestMapping("/")
//    public String helloAdmin() {
//        return "hello admin";
//    }
//
//    @RequestMapping("/user/save")
//    public void saveUser(@RequestBody UserRequest userRequest) {
//        log.info(userRequest.toString());
//        userService.addUser(userRequest);
//    }
//}
