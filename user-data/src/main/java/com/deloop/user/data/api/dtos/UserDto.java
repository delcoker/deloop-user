package com.deloop.user.data.api.dtos;

import com.deloop.user.data.db.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements UserDetails, Serializable {
    //    @JsonProperty

    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String email = "";

    @Builder.Default
    private String username = "";

    @Builder.Default
    private boolean isVerified = false;

    @Builder.Default
    private String status = "";

    @Builder.Default
    private UserDetailDto userDetails = UserDetailDto.builder().build();

    @Builder.Default
    private List<ProviderAccountDto> providerAccounts = new ArrayList<>();

    @Builder.Default
    //    private UserTypeDto userType = ";
    private UserRoleDto userRole = UserRoleDto.builder().build();

    @Builder.Default
    private LicenseTypeDto licenseType = LicenseTypeDto.builder().build();

    @Builder.Default
    @JsonIgnore
    private String password = "";

    @Builder.Default
    private boolean locked = true;

    //    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.toString());
        return Collections.singletonList(authority);
    }

    // Basic spring USERDETAILS data... not actually full user details

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    } // using email as username auth

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // should be something else?

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // should be something else?

    @Override
    public boolean isEnabled() {
        return UserStatus.getStatus(status) == UserStatus.ENABLED;
    }
}
