package com.deloop.user.data.api.dtos;

import com.deloop.user.data.db.enums.UserStatus;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder
public class UserDto implements UserDetails {
    private long id;
    private String email;
    private String username;
    private boolean isVerified;
    private String status;
    private UserDetailDto userDetails;
    private List<ProviderAccountDto> providerAccounts;
//    private UserTypeDto userType;
    private UserRoleDto userRole;
    private LicenseTypeDto licenseType;
    private String password;
    private boolean locked;
    //    private String firstName;
//    private String lastName;
//    @Enumerated(EnumType.STRING)
//    private String appUserRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.toString());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
//
//    public String getFirstName() {
//        return userDetails.get;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserStatus.getStatus(status) == UserStatus.ENABLED;
    }
}
