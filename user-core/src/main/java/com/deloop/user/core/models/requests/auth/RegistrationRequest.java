package com.deloop.user.core.models.requests.auth;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RegistrationRequest {

    @Builder.Default
    @ApiParam(required = true)
    private String email = "";

    @Builder.Default
    @ApiParam(required = true)
    private String password = "";

    @Builder.Default
    @ApiParam(required = true)
    private String verifyPassword = "";

    @Builder.Default
    private String username = "";

    @ApiParam(hidden = true)
    public boolean isValid() {
        return password.equals(verifyPassword);
    }


//    private final String firstName;
//    private final String lastName;

//    private UserStatus status;
//    private List<UserDetail> userDetails;
//    private List<ProviderAccount> providerAccounts;
//    private UserType userType;
//    private LicenseType licenseType;
//    private UserRole userRole;
}
