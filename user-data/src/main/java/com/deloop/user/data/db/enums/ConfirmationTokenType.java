package com.deloop.user.data.db.enums;

import io.ebean.annotation.DbEnumValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Confirmation token type.
 */
@RequiredArgsConstructor
public enum ConfirmationTokenType {

    /**
     * Confirm email address.
     */
    EMAIL("email"),

    /**
     * Confirm password reset.
     */
    PASSWORD_RESET("password_reset");

    private final String label;

    @DbEnumValue
    public String getLabel() {
        return this.label;
    }

    public static ConfirmationTokenType getConfirmationTokenType(String genderString) {
        return Arrays.stream(ConfirmationTokenType.values())
                .filter(confirmationTokenType -> compareConfirmationTokenType(confirmationTokenType, genderString))
                .findFirst()
                .orElse(ConfirmationTokenType.EMAIL);
    }

    private static boolean compareConfirmationTokenType(ConfirmationTokenType confirmationTokenType, String confirmationTokenTypeString) {
        return confirmationTokenType.getLabel().charAt(0) == confirmationTokenTypeString.toLowerCase().charAt(0);
    }
}
