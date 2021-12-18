package com.deloop.user.data.api.dtos;

import com.deloop.user.data.db.enums.Country;
import com.deloop.user.data.db.enums.State;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class AddressDto implements Serializable {
    private long id;
    private Country country;
    private State state;
    private String city;
    private String addressLine1;
    private String addressLine2;
    private String postCode;
    private String addressType;
}
