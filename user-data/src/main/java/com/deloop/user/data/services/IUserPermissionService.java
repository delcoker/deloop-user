package com.deloop.user.data.services;

import com.deloop.user.data.api.requests.UserPermissionRequest;

public interface IUserPermissionService {
    void addUserPermission(UserPermissionRequest userPermissionRequest);
}