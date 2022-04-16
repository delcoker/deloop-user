package com.deloop.user.core.models.requests;

import com.deloop.user.data.db.enums.AddressType;
import com.deloop.user.data.db.enums.Country;
import com.deloop.user.data.db.enums.State;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
//@Setter
@Builder
@ToString
public class AddAddressRequest {

    @Builder.Default
    @ApiParam(example = "-1")
    private long userId = -1;

    @Builder.Default
    @ApiParam(example = "GH")
    private Country country = Country.GH;

    @Builder.Default
    @ApiParam(example = "AC")
    private State state = State.AC;

    @Builder.Default
    @ApiParam(example = "Accra")
    private String city = "";

    @Builder.Default
//    @ApiParam(example = "")
    private String addressLine1 = "";

    @Builder.Default
//    @ApiParam(example = "-")
    private String addressLine2 = "";

    @Builder.Default
//    @ApiParam(example = "")
    private String postCode = "";

    @Builder.Default
    @ApiParam(example = "HOME")
    private AddressType addressType = AddressType.HOME;
}
