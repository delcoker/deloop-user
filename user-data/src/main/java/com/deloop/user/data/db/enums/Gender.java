
package com.deloop.user.data.db.enums;

import io.ebean.annotation.DbEnumValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * This enum represents genders with that option if it's not known of if someone would rather not
 *     state it.
 */
@RequiredArgsConstructor
public enum Gender {

    /**
     * Female.
     */
    FEMALE("female"),

    /**
     * Male.
     */
    MALE("male"),

    /**
     * Unknown.
     */
    UNKNOWN("unknown");


    private final String label;

    @DbEnumValue
    public String getLabel() {
        return this.label;
    }

    public static Gender getGender(String genderString) {
        return Arrays.stream(Gender.values())
                .filter(gender -> compareGender(gender, genderString))
                .findFirst()
                .orElse(Gender.UNKNOWN);
    }

    private static boolean compareGender(Gender gender, String genderString) {
        return gender.getLabel().charAt(0) == genderString.toLowerCase().charAt(0);
    }
}
