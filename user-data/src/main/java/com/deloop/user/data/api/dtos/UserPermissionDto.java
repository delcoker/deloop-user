package com.deloop.user.data.api.dtos;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class UserPermissionDto implements Serializable {

    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String code = "";

    @Builder.Default
    private String description = "";

    @Builder.Default
    private String status = "";
}
