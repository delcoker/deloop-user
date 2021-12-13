package com.deloop.user.data.api.dtos;

import lombok.Builder;

@Builder
public class UserPermissionDto {
    private long id;
    private String name;
    private String code;
    private String description;
    private String status;
}
