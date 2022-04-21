package com.deloop.user.data.api.dtos;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
//@Setter
public class UserRoleDto implements Serializable {

    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String description = "";

    @Builder.Default
    private String capabilities = "";

    @Builder.Default
    private String status = "";

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.MIN;

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.MIN;

    @Builder.Default
    private List<UserPermissionDto> userPermissions = new ArrayList<>();
}
