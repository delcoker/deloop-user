package com.deloop.user.data.api.dtos;

import lombok.Builder;

@Builder
public class ConfirmationTokenDto {
    private long id;
    private String token;
    private String createdAt;
    private String expiresAt;
    private String confirmedAt;
}
