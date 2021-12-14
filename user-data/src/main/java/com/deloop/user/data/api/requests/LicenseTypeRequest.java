package com.deloop.user.data.api.requests;

import com.deloop.user.data.db.enums.LicenseStatus;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class LicenseTypeRequest {
    private long id;
    private String name;
    private String description;
    private String access;
    private LicenseStatus status;
}
