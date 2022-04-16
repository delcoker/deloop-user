package com.deloop.user.core.models.requests;

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
    private String verifyEmail = "";

    @Builder.Default
    @ApiParam(required = true)
    private String password = "";

    @Builder.Default
    private String username = "";

    public boolean isValid() {
        return email.equalsIgnoreCase(verifyEmail);
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
