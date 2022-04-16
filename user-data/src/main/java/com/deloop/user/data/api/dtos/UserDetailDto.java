package com.deloop.user.data.api.dtos;

import com.deloop.user.data.db.enums.Gender;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class UserDetailDto implements Serializable {

    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String profilePicture = "";

    @Builder.Default
    private String firstName = "";

    @Builder.Default
    private String otherNames = "";

    @Builder.Default
    private String lastName = "";

    @Builder.Default
    private String fullName = "";

    @Builder.Default
    private String titledFullName = "";

    @Builder.Default
    private String shortName = "";

    @Builder.Default
    private String initials = "";

    @Builder.Default
    private int age = -1;

    @Builder.Default
    private Gender gender = Gender.MALE;

    @Builder.Default
    private List<AddressDto> addresses = new ArrayList<>();

    @Builder.Default
    private LocalDateTime dateOfBirth = LocalDateTime.MIN;

    @Builder.Default
    private String placeOfBirth = "";

    @Builder.Default
    private String prefix = "";

    @Builder.Default
    private String title = "";

    @Builder.Default
    private String memo = "";


    private boolean locked; // TODO: do i need this here
    private boolean enabled; // TODO: do i need this here

//
//    public int getAge() {
//        if (dateOfBirth == null) {
//            return -1;
//        }
//        return Period.between(dateOfBirth.toLocalDate(), LocalDate.now()).getYears() + 1;
//    }
//
//    public String getFullName() {
//        return String.format("%s %s %s", firstName, otherNames, lastName);
//    }
//
//    public String getTitledFullName() {
//        return String.format("%s %s %s", title, prefix, getFullName());
//    }
//
//    public String getShortName() {
//        return String.format("%s %s %s", prefix, getInitials(), lastName);
//    }
//
//    public String getInitials() {
//        if (otherNames == null || otherNames.length() < 1) {
//            return "";
//        }
//        return String.format("%s. %s. %s", String.valueOf(firstName.charAt(0)).toUpperCase(),
//                String.valueOf(otherNames.charAt(0)).toUpperCase(), lastName);
//    }
}