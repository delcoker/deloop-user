package com.deloop.user.data.api.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class LicenseTypeDto {

    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String description = "";

    @Builder.Default
    private String access = "";

    @Builder.Default
    private String status = "";

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.MIN;

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.MIN;
}
