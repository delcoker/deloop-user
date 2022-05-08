package com.deloop.user.data.db.models;

import com.deloop.user.data.api.dtos.AddressDto;
import com.deloop.user.data.db.enums.AddressType;
import com.deloop.user.data.db.enums.Country;
import com.deloop.user.data.db.enums.State;
import io.ebean.Model;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@Entity
@Table(name = "address")
public class Address extends Model {
    @Id
    private long id;

    @ManyToOne
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    private UserDetail userDetail;

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

    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime createdAt;

    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime updatedAt;

    public AddressDto getAddressDto() {
        return AddressDto.builder()
                .id(id)
                .addressType(addressType) // need address type dto?
                .addressLine1(addressLine1)
                .addressLine2(addressLine2)
                .country(country)
                .state(state)
                .city(city)
                .postCode(postCode)
                .build();
    }
}
