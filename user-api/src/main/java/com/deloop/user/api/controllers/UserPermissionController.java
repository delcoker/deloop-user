package com.deloop.user.api.controllers;


import com.deloop.user.core.models.requests.AddUserPermissionRequest;
import com.deloop.user.core.services.role.IUserPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
public class UserPermissionController {

    private final IUserPermissionService userPermissionService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@SpringQueryMap AddUserPermissionRequest addUserPermissionRequest) {

        userPermissionService.addUserPermission(addUserPermissionRequest);

        return ResponseEntity.ok("N");
    }
}
