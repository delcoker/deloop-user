package com.deloop.user.core.models.requests.userdetails;

import com.deloop.user.data.db.enums.Gender;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
//@AllArgsConstructor
public class AddOrUpdateUserDetailRequest implements Serializable {
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
    @ApiParam(example = "MALE")
    private Gender gender = Gender.MALE;

//    @Builder.Default
//    @ApiParam(example = "home")
//    private AddressDto address = AddressDto.builder().build();

    @Builder.Default
    @ApiParam(example = "1999-04-26T00:00", format = "yyyy-mm-ddThh:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
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

//    @Builder.Default
//    @ApiParam(example = "1999-04-26")
////    @ApiParam(example = "1999-04-26T08:00:19Z")
//    private LocalDate lastLogin = LocalDate.now();

    @Builder.Default
    @ApiParam(example = "-1")
    private long userId = -1;
}
