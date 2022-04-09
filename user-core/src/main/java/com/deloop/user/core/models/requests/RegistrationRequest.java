package com.deloop.user.core.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class RegistrationRequest {
    private String email;
    private String password;
    private String username;
//    private final String firstName;
//    private final String lastName;

//    private UserStatus status;
//    private List<UserDetail> userDetails;
//    private List<ProviderAccount> providerAccounts;
//    private UserType userType;
//    private LicenseType licenseType;
//    private UserRole userRole;
}
