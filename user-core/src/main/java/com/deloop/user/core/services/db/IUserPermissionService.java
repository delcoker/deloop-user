package com.deloop.user.core.services;

import com.deloop.user.data.api.requests.UserPermissionRequest;

public interface IUserPermissionService {
    void addUserPermission(UserPermissionRequest userPermissionRequest);
}