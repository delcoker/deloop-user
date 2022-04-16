package com.deloop.user.core.models.requests;

import com.deloop.user.data.db.enums.UserStatus;
import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.models.UserRole;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
//@AllArgsConstructor
public class AddUserRequest {

    @Builder.Default
    private long id = -1;

    @Builder.Default
    @ApiParam(required = true)
    private String email = "";

    @Builder.Default
    private String password = "";

    @Builder.Default
    private String username = "";

    @Builder.Default
    private UserStatus status = UserStatus.DISABLED;

    @Builder.Default
    @ApiParam(example = "false")
    private boolean isVerified = false;
    //    private List<UserDetail> userDetails;
//    private List<ProviderAccount> providerAccounts;
//    private UserType userType;
    @Builder.Default
    private LicenseType licenseType = LicenseType.builder().build();

    @Builder.Default
    private UserRole userRole = UserRole.builder().build();
}
