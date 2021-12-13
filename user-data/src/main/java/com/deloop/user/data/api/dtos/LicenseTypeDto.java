package com.deloop.user.data.api.dtos;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class LicenseTypeDto {
    private long id;
    private String name;
    private String description;
    private String access;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
