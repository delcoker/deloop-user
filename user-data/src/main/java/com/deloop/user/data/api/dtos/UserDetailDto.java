package com.deloop.user.data.api.dtos;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserDetailDto implements Serializable {
    private long id;
    private String profilePicture;
    private String firstName;
    private String otherNames;
    private String lastName;
    private String fullName;
    private String titledFullName;
    private String shortName;
    private String initials;
    private int age;
    private String gender;
    private List<AddressDto> addresses;
    private LocalDateTime dateOfBirth;
    private String placeOfBirth;
    private String prefix;
    private String title;
    private String memo;


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