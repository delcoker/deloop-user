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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements UserDetails, Serializable {
    //    @JsonProperty
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
    @JsonIgnore
    private String password;
    private boolean locked;

    //    @JsonIgnore
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
    } // using email as username auth

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
