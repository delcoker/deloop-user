package com.deloop.user.core.models.requests;

import com.deloop.user.data.db.enums.RoleStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
//@AllArgsConstructor
public class UserRoleRequest {

    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String description = "";

    @Builder.Default
    private RoleStatus status = RoleStatus.DISABLED;

    @Builder.Default
    private String capabilities = "";
}
