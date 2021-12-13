package com.deloop.user.data.api.dtos;

import lombok.Builder;

@Builder
public class UserRoleDto {
    private long id;
    private String name;
    private String description;
    private String capabilities;
    private String status;
}
