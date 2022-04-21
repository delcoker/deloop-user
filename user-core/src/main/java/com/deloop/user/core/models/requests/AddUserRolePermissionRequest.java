package com.deloop.user.core.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
//@AllArgsConstructor
public class AddUserRolePermissionRequest {

    @Builder.Default
    private long id = -1;

    @Builder.Default
    private long roleId = -1;

    @Builder.Default
    private long permissionId = -1;
}
