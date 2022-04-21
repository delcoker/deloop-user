package com.deloop.user.data.api.dtos;

import lombok.Builder;

@Builder
public class ConfirmationTokenDto {
    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String token = "";

    @Builder.Default
    private String createdAt = "";

    @Builder.Default
    private String expiresAt = "";

    @Builder.Default
    private String confirmedAt = "";
}
