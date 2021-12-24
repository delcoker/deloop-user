package com.deloop.user.data.db.models;

import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.db.enums.AddressType;
import com.deloop.user.data.db.enums.Country;
import com.deloop.user.data.db.enums.State;
import io.ebean.annotation.DbDefault;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "address")
public class Address {
    @Id
    private long id;

    @Column
    private Country country;

    @Column
    private State state;

    @Column
    private String city;

    @Column
    @DbDefault("")
    private String addressLine1;

    @Column
    @DbDefault("")
    private String addressLine2;

    @Column
    @DbDefault("")
    private String postCode;

    @Enumerated(value = EnumType.STRING)
    private AddressType addressType;

    @ManyToOne
    private UserDetail userDetail;

    public AddressDto getAddressDto() {
        return AddressDto.builder()
                .id(id)
                .addressType(addressType.getLabel()) // need address type dto?
                .addressLine1(addressLine1)
                .addressLine2(addressLine2)
                .country(country)
                .state(state)
                .city(city)
                .postCode(postCode)
                .build();
    }
}
