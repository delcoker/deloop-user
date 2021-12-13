
package com.deloop.user.data.db.enums;

import io.ebean.annotation.DbEnumValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * This enum represents genders with that option if it's not known of if someone would rather not
 * state it.
 */
@RequiredArgsConstructor
public enum RoleStatus {

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

    public static RoleStatus getStatus(String statusString) {
        return Arrays.stream(RoleStatus.values())
                .filter(gender -> compareStatus(gender, statusString))
                .findFirst()
                .orElse(RoleStatus.DELETED);
    }

    private static boolean compareStatus(RoleStatus status, String statusString) {
        return status.getLabel().equalsIgnoreCase(statusString);
    }
}
