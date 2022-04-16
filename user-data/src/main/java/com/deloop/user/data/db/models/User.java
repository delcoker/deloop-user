package com.deloop.user.data.db.models;

import com.deloop.user.data.api.dtos.*;
import com.deloop.user.data.db.enums.Gender;
import com.deloop.user.data.db.enums.UserStatus;
import io.ebean.annotation.DbDefault;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;

    @Column
    @DbDefault("")
    private String username;

    @Column
    @NonNull
    private String email;

    @Column
    @NonNull
    private String password;

    @Column
    private boolean isVerified;

    @Column
    private boolean locked;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private UserStatus status = UserStatus.ENABLED;

    @OneToMany(mappedBy = "user") // supposed to be @OneToOne
    private List<UserDetail> userDetails;

    @OneToMany(mappedBy = "user")
    private List<ProviderAccount> providerAccounts;

//    @ManyToOne
//    private UserType userType;

    @ManyToOne
    private LicenseType licenseType;

    @ManyToOne
    private UserRole userRole;

    public UserDto getUserDto() {
        List<ProviderAccountDto> providerAccountDtos = providerAccounts.stream()
                .map(providerAccount -> new ProviderAccountDto(providerAccount.getId(),
                        providerAccount.getProvider(),
                        providerAccount.getProfileLink()))
                .collect(Collectors.toList());

        List<UserPermissionDto> userPermissionDtos = userRole.getUserPermissions().stream()
                .map(userPermission -> UserPermissionDto.builder()
                        .id(userPermission.getId())
                        .name(userPermission.getName())
                        .code(userPermission.getCode())
                        .description(userPermission.getDescription())
                        .status(userPermission.getStatus().getLabel())
                        .build())
                .collect(Collectors.toList());

//        UserTypeDto userTypeDto = UserTypeDto.builder()
//                .id(userType.getId())
//                .name(userType.getName())
//                .access(userType.getAccess())
//                .userPermissions(userPermissionDtos)
//                .build();

        UserRoleDto userRoleDto = UserRoleDto.builder()
                .id(userRole.getId())
                .name(userRole.getName())
                .description(userRole.getDescription())
                .capabilities(userRole.getCapabilities())
                .status(userRole.getStatus().getLabel())
                .userPermissions(userPermissionDtos)
                .createdAt(userRole.getCreatedAt())
                .updatedAt(userRole.getUpdatedAt())
                .build();

        LicenseTypeDto licenseTypeDto = LicenseTypeDto.builder()
                .id(licenseType.getId())
                .name(licenseType.getName())
                .access(licenseType.getAccess())
                .description(licenseType.getDescription())
                .status(licenseType.getStatus().getLabel())
                .build();

        UserDetail userDetail = userDetails.stream()
                .findFirst()
                .orElse(UserDetail.builder()
                        .gender(Gender.UNKNOWN)
                        .build());

        List<AddressDto> addresses = userDetail.getAddresses().stream()
                .map(Address::getAddressDto)
                .collect(Collectors.toList());

        UserDetailDto userDetailDto = UserDetailDto.builder()
                .id(userDetail.getId())
                .age(userDetail.getAge())
                .firstName(userDetail.getFirstName())
                .otherNames(userDetail.getOtherNames())
                .lastName(userDetail.getLastName())
                .fullName(userDetail.getFullName())
                .titledFullName(userDetail.getTitledFullName())
                .shortName(userDetail.getShortName())
                .initials(userDetail.getInitials())
                .gender(userDetail.getGender())
                .dateOfBirth(userDetail.getDateOfBirth())
                .addresses(addresses)
                .build();

        return UserDto.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .isVerified(isVerified)
                .status(status.getLabel())
                .userDetails(userDetailDto)
                .providerAccounts(providerAccountDtos)
                .locked(this.locked)
//                .userType(userTypeDto)
                .licenseType(licenseTypeDto)
                .userRole(userRoleDto)
                .build();
    }
}
