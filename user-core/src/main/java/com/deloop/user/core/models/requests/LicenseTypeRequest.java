package com.deloop.user.core.models.requests;

import com.deloop.user.data.db.enums.LicenseStatus;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
//@AllArgsConstructor
public class LicenseTypeRequest {
    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String description = "";

    @Builder.Default
    private String access = "";

    @Builder.Default
    private LicenseStatus status = LicenseStatus.DISABLED;
}
