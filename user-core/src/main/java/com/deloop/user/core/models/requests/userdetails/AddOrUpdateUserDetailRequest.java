package com.deloop.user.core.models.requests.userdetails;

import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.db.enums.Gender;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
//@Setter
@Builder
@ToString
//@AllArgsConstructor
public class AddOrUpdateUserDetailRequest {
//    @Builder.Default
//    private long id = -1;

    @Builder.Default
    private String profilePicture = "";

    @Builder.Default
    @ApiParam(example = "Kingston")
    private String firstName = "";

    @Builder.Default
    private String otherNames = "";

    @Builder.Default
    @ApiParam(example = "Coker")
    private String lastName = "";

    @Builder.Default
    @ApiParam(example = "male")
    private Gender gender = Gender.MALE;

    @Builder.Default
    @ApiParam(example = "home")
    private List<AddressDto> addresses = new ArrayList<>();

    @Builder.Default
    @ApiParam(example = "1999-04-26T08:00:19")
    private LocalDateTime dateOfBirth = LocalDateTime.MIN;

    @Builder.Default
    @ApiParam(example = "Accra")
    private String placeOfBirth = "";

    @Builder.Default
    @ApiParam(example = "Mr.")
    private String prefix = "";

    @Builder.Default
    @ApiParam(example = "")
    private String title = "";

    @Builder.Default
    @ApiParam(example = "no memo")
    private String memo = "";

    @Builder.Default
    @ApiParam(example = "1999-04-26 00:00:00")
//    @ApiParam(example = "1999-04-26T08:00:19Z")
    private LocalDateTime lastLogin = LocalDateTime.now();

    @Builder.Default
    @ApiParam(example = "-1")
    private long userId = -1;
}
