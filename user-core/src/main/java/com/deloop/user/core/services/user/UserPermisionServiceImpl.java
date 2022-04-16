package com.deloop.user.core.services.user;

import com.deloop.user.core.models.requests.UserPermissionRequest;
import com.deloop.user.data.db.models.UserPermission;
import com.deloop.user.data.db.repositories.IUserPermissionRepository;
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
