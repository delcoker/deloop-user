
package com.deloop.user.data.db.enums;

import io.ebean.annotation.DbEnumValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * This enum represents genders with that option if it's not known of if someone would rather not
 * state it.
 */
@RequiredArgsConstructor
public enum TypeStatus {

    /**
     * enabled
     */
    ENABLED("enabled"),

    /**
     * disabled.
     */
    DISABLED("disabled"),

    /**
     * deleted.
     */
    DELETED("deleted");


    private final String label;

    @DbEnumValue
    public String getLabel() {
        return this.label;
    }

    public static TypeStatus getStatus(String statusString) {
        return Arrays.stream(TypeStatus.values())
                .filter(gender -> compareStatus(gender, statusString))
                .findFirst()
                .orElse(TypeStatus.DELETED);
    }

    private static boolean compareStatus(TypeStatus status, String statusString) {
        return status.getLabel().equalsIgnoreCase(statusString);
    }
}
