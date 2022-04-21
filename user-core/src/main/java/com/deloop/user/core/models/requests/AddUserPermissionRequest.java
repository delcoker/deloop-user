package com.deloop.user.core.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
//@Setter
@Builder
@ToString
public class AddUserPermissionRequest {

    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String code = "";

    @Builder.Default
    private String description = "";
}
