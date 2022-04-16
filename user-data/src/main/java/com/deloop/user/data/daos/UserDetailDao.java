package com.deloop.user.data.daos;

import com.deloop.user.data.api.dtos.AddressDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString
public class UserDetailDao {

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
    private String gender = "";

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

    @Builder.Default
    private LocalDateTime lastLogin = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.MIN;

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder.Default
    private long userId = -1;

}
