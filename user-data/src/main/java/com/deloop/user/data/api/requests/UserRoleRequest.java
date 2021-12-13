package com.deloop.user.data.api.requests;

import com.deloop.user.data.db.enums.RoleStatus;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class UserRoleRequest {
    private long id;
    private String name;
    private String description;
    private RoleStatus status;
    private String capabilities;
}
