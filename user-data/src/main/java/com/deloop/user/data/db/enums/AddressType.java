package com.deloop.user.data.db.enums;

import io.ebean.annotation.DbEnumValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum AddressType {

    /**
     * Home address.
     */
    HOME("home"),

    /**
     * Billing address.
     */
    BILLING("billing"),

    /**
     * Shipping address.
     */
    SHIPPING("shipping"),

    /**
     * Unknown.
     */
    UNKNOWN("unknown");;

    private final String label;

    @DbEnumValue
    public String getLabel() {
        return this.label;
    }

    public static AddressType getAddressTypeFromText(String addressTypeString) {
        return Arrays.stream(AddressType.values())
                .filter(addressType -> compareAddressType(addressType, addressTypeString))
                .findFirst()
                .orElse(AddressType.UNKNOWN);
    }

    private static boolean compareAddressType(AddressType addressType, String addressTypeString) {
        return addressType.getLabel().equalsIgnoreCase(addressTypeString.toLowerCase());
    }

}
