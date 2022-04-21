
package com.deloop.user.data.db.enums;

import io.ebean.annotation.DbEnumValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * https://success.clarizen.com/hc/en-us/articles/206448817-Intro-to-User-Types-Roles-and-Permissions
 * This enum represents genders with that option if it's not known of if someone would rather not
 * state it.
 */
@RequiredArgsConstructor
public enum PermissionStatus {

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

    public static PermissionStatus getStatus(String statusString) {
        return Arrays.stream(PermissionStatus.values())
                .filter(gender -> compareStatus(gender, statusString))
                .findFirst()
                .orElse(PermissionStatus.DELETED);
    }

    private static boolean compareStatus(PermissionStatus status, String statusString) {
        return status.getLabel().equalsIgnoreCase(statusString);
    }
}
