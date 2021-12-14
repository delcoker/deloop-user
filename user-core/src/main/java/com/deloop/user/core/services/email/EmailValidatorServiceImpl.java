package com.deloop.user.core.services;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorServiceImpl implements EmailValidatorService {
    private static final Pattern EMAIL_ADDRESS_PATTERN =
            Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)"
                    + "+[\\w](?:[\\w-]*[\\w])?");

    @Override
    public boolean test(String email) {
        //   TODO: regex to validate email is valid
        if (Objects.isNull(email)) {
            return false;
        }
        Matcher matcher = EMAIL_ADDRESS_PATTERN.matcher(email);
        return matcher.matches();
    }

//    @Override
//    public boolean isEmail(String email) {
//        //        TODO: regex to validate email is valid
//        if (Objects.isNull(email)) {
//            return false;
//        }
//        Matcher matcher = EMAIL_ADDRESS_PATTERN.matcher(email);
//        return matcher.matches();
//
//    }
}
