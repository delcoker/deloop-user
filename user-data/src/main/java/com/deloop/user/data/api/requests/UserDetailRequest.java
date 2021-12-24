package com.deloop.user.data.api.requests;

import com.deloop.user.data.api.dtos.AddressDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class UserDetailRequest {
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
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private long userId;
}
