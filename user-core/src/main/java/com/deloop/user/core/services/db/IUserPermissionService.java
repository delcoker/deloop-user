package com.deloop.user.core.services.db;

import com.deloop.user.data.api.requests.UserPermissionRequest;

public interface IUserPermissionService {
    void addUserPermission(UserPermissionRequest userPermissionRequest);
}