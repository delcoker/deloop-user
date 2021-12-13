package com.deloop.user.data.db.models;

import com.deloop.user.data.api.dtos.*;
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

    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user") // @OneToOne
    private List<UserDetail> userDetails;

    @OneToMany(mappedBy = "user")
    private List<ProviderAccount> providerAccounts;

    @ManyToOne
    private UserType userType;

    @ManyToOne
    private LicenseType licenseType;

    @ManyToOne
    private UserRole userRole;


    private UserDto getUserDto() {
        List<ProviderAccountDto> providerAccountDtos = providerAccounts.stream()
                .map(providerAccount -> new ProviderAccountDto(providerAccount.getId(),
                        providerAccount.getProvider(),
                        providerAccount.getProfileLink()))
                .collect(Collectors.toList());

        List<UserPermissionDto> userPermissionDtos = userType.getUserPermissions().stream()
                .map(userPermission -> UserPermissionDto.builder()
                        .id(userPermission.getId())
                        .name(userPermission.getName())
                        .code(userPermission.getCode())
                        .description(userPermission.getDescription())
                        .status(userPermission.getStatus().getLabel())
                        .build())
                .collect(Collectors.toList());

        UserTypeDto userTypeDto = UserTypeDto.builder()
                .id(userType.getId())
                .name(userType.getName())
                .access(userType.getAccess())
                .userPermissions(userPermissionDtos)
                .build();

        UserRoleDto userRoleDto = UserRoleDto.builder()
                .id(userRole.getId())
                .name(userRole.getName())
                .description(userRole.getDescription())
                .capabilities(userRole.getCapabilities())
                .status(userRole.getStatus().getLabel())
                .build();

        LicenseTypeDto licenseTypeDto = LicenseTypeDto.builder()
                .id(licenseType.getId())
                .name(licenseType.getName())
                .access(licenseType.getAccess())
                .description(licenseType.getDescription())
                .status(licenseType.getStatus().getLabel())
                .build();

        UserDetail userDetail = userDetails.stream().findFirst().orElse(UserDetail.builder().build());
        List<AddressDto> addresses = userDetail.getAddresses().stream()
                .map(this::getAddressDto)
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
                .gender(userDetail.getGender().getLabel())
                .dateOfBirth(userDetail.getDateOfBirth())
                .addresses(addresses)
                .build();

        return UserDto.builder()
                .email(email)
                .isVerified(isVerified)
                .status(status.getLabel())
                .userDetails(userDetailDto)
                .providerAccounts(providerAccountDtos)
                .userType(userTypeDto)
                .licenseType(licenseTypeDto)
                .userRole(userRoleDto)
                .build();
    }

    private AddressDto getAddressDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .addressType(address.getAddressType().name())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .country(address.getCountry())
                .state(address.getState())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .build();
    }
}
