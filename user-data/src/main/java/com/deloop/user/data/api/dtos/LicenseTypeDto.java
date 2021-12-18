package com.deloop.user.data.api.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class LicenseTypeDto {
    private long id;
    private String name;
    private String description;
    private String access;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
