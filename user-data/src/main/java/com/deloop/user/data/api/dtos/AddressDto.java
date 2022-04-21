package com.deloop.user.data.api.dtos;

import com.deloop.user.data.db.enums.AddressType;
import com.deloop.user.data.db.enums.Country;
import com.deloop.user.data.db.enums.State;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class AddressDto implements Serializable {
    @Builder.Default
    private long id = -1;

    @Builder.Default
    private Country country = Country.GH;

    @Builder.Default
    private State state = State.AC;

    @Builder.Default
    private String city = "";

    @Builder.Default
    private String addressLine1 = "";

    @Builder.Default
    private String addressLine2 = "";

    @Builder.Default
    private String postCode = "";

    @Builder.Default
    private AddressType addressType = AddressType.HOME;
}
