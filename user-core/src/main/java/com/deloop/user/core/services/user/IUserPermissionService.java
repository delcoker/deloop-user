package com.deloop.user.core.services.user;

import com.deloop.user.core.models.requests.UserPermissionRequest;

public interface IUserPermissionService {
    void addUserPermission(UserPermissionRequest userPermissionRequest);
}