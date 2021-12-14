package com.deloop.user.core.services;

import com.deloop.user.data.api.requests.UserPermissionRequest;
import com.deloop.user.data.db.repositories.IUserPermissionRepository;
import com.deloop.user.data.db.models.UserPermission;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPermisionServiceImpl implements IUserPermissionService {
    public final IUserPermissionRepository userPermissionRepository;

    @Override
    public void addUserPermission(UserPermissionRequest userPermissionRequest) {
        UserPermission userPermission = UserPermission.builder()
                .name(userPermissionRequest.getName())
                .description(userPermissionRequest.getDescription())
                .code(userPermissionRequest.getCode())
                .build();
        userPermissionRepository.save(userPermission);
    }
}
