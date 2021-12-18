package com.deloop.user.data.api.dtos;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class UserPermissionDto implements Serializable {
    private long id;
    private String name;
    private String code;
    private String description;
    private String status;
}
