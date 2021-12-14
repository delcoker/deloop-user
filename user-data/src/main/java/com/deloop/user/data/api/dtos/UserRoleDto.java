package com.deloop.user.data.api.dtos;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class UserRoleDto {
    private long id;
    private String name;
    private String description;
    private String capabilities;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<UserPermissionDto> userPermissions;
}
