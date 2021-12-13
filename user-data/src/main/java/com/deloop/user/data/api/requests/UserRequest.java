package com.deloop.user.data.api.requests;

import com.deloop.user.data.db.models.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class UserRequest {
    private long id;
    private String email;
    private String password;
    private String username;
//    private UserStatus status;
//    private List<UserDetail> userDetails;
//    private List<ProviderAccount> providerAccounts;
//    private UserType userType;
//    private LicenseType licenseType;
    private UserRole userRole;
}
