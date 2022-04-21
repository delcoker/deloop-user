package com.deloop.user.core.services.role;

import com.deloop.user.core.models.requests.AddUserPermissionRequest;
import com.deloop.user.data.db.models.UserPermission;
import com.deloop.user.data.db.repositories.roles.IUserPermissionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPermisionServiceImpl implements IUserPermissionService {
    public final IUserPermissionRepository userPermissionRepository;

    @Override
    public void addUserPermission(AddUserPermissionRequest addUserPermissionRequest) {
        UserPermission userPermission = UserPermission.builder()
                .name(addUserPermissionRequest.getName())
                .description(addUserPermissionRequest.getDescription())
                .code(addUserPermissionRequest.getCode())
                .build();
        userPermissionRepository.save(userPermission);
    }
}
