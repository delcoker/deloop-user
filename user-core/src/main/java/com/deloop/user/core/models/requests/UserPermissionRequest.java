package com.deloop.user.core.models.requests;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class UserPermissionRequest {
    private long id;
    private String name;
    private String code;
    private String description;
}
