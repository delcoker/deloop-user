package com.deloop.user.data.api.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class UserRoleDto implements Serializable {
    private long id;
    private String name;
    private String description;
    private String capabilities;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<UserPermissionDto> userPermissions;
}
