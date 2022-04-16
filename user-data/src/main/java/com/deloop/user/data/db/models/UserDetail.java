package com.deloop.user.data.db.models;

import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.api.dtos.UserDetailDto;
import com.deloop.user.data.db.enums.Gender;
import io.ebean.annotation.DbDefault;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@Entity
@Table(name = "user_details")
public class UserDetail {
    @Id
    private long id;

    @Column
    @Builder.Default
    private String profilePicture = "";

    @Column
    @DbDefault("")
    @Builder.Default
    private String firstName = "";

    @Column(columnDefinition = "VARCHAR(100)")
    @DbDefault("")
    @Builder.Default
    private String otherNames = "";

    @Column(columnDefinition = "VARCHAR(100)")
    @DbDefault("")
    @Builder.Default
    private String lastName = "";

    @Transient
    @DbDefault("")
    @Builder.Default
    private String fullName = "";

    @Transient
    @DbDefault("")
    @Builder.Default
    private String titledFullName = "";

    @Transient
    @DbDefault("")
    @Builder.Default
    private String shortName = "";

    @Transient
    @DbDefault("")
    @Builder.Default
    private String initials = "";

    @Transient
    @Builder.Default
    private int age = -1; // do I need this property

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.UNKNOWN;

    @Column
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime dateOfBirth;

    @Column
    @DbDefault("")
    private String placeOfBirth;

    @Column
    @DbDefault("")
    @Builder.Default
    private String prefix = "";

    @Column(columnDefinition = "VARCHAR(100)")
    @DbDefault("")
    @Builder.Default
    private String title = "";

    @Column(columnDefinition = "TEXT")
    @DbDefault("")
    @Builder.Default
    private String memo = "";

    @Column
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime lastLogin;

    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime createdAt = LocalDateTime.MIN;

    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "userDetail")
    private List<Address> addresses;

    public int getAge() {
        if (dateOfBirth == null) {
            return -1;
        }
        return Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears();
    }

    public String getFullName() {
        return String.format("%s %s %s", firstName, otherNames, lastName);
    }

    public String getTitledFullName() {
        return String.format("%s %s %s", title, prefix, getFullName());
    }

    public String getShortName() {
        return String.format("%s %s %s", prefix, getInitials(), lastName);
    }

    public String getInitials() {
        if (otherNames == null || otherNames.length() < 1) {
            return "";
        }
        return String.format("%s. %s. %s", String.valueOf(firstName.charAt(0)).toUpperCase(),
                String.valueOf(otherNames.charAt(0)).toUpperCase(), lastName);
    }

    public UserDetailDto getUserDetailDto() {
        List<AddressDto> addressesDtos = addresses.stream()
                .map(Address::getAddressDto)
                .collect(Collectors.toList());

        return UserDetailDto.builder()
                .addresses(addressesDtos)
                .id(id)
                .profilePicture(profilePicture)
                .firstName(firstName)
                .otherNames(otherNames)
                .lastName(lastName)
                .fullName(getFullName())
                .titledFullName(getTitledFullName())
                .shortName(getShortName())
                .initials(getInitials())
                .age(getAge())
                .gender(gender)
                .dateOfBirth(getDateOfBirth())
                .placeOfBirth(getPlaceOfBirth())
                .prefix(prefix)
                .title(title)
                .memo(memo)
                .locked(user.isLocked()) //TODO: do i need this here
                .build();
    }
}