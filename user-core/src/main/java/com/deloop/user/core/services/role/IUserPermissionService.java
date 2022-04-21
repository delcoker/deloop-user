package com.deloop.user.core.services.role;

import com.deloop.user.core.models.requests.AddUserPermissionRequest;

public interface IUserPermissionService {
    void addUserPermission(AddUserPermissionRequest addUserPermissionRequest);
}