package com.deloop.user.data.api.requests;

import com.deloop.user.data.db.enums.UserStatus;
import com.deloop.user.data.db.models.UserRole;
import lombok.*;

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

    private UserStatus status;
//    private List<UserDetail> userDetails;
//    private List<ProviderAccount> providerAccounts;
//    private UserType userType;
//    private LicenseType licenseType;
    private UserRole userRole;
}
