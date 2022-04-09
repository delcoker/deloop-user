package com.deloop.user.core.services;

import com.deloop.user.core.models.requests.UserPermissionRequest;

public interface IUserPermissionService {
    void addUserPermission(UserPermissionRequest userPermissionRequest);
}