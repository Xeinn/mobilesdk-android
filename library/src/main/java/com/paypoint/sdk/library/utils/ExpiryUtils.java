package com.paypoint.sdk.library.utils;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Who:  Pete
 * When: 15/04/2015
 * What:
 */
public class ExpiryUtils {

    private static final int LENGTH_EXPIRY_DATE = 4;

    public static boolean isValid(String expiry) {

        if (TextUtils.isEmpty(expiry)) {
            return false;
        }

        if (expiry.length() != LENGTH_EXPIRY_DATE) {
            return false;
        }

        // check all numeric
        if (!TextUtils.isDigitsOnly(expiry)) {
            return false;
        }

        return true;
    }

    public static boolean isCardExpired(String expiry, Date referenceDate) {
        boolean expired = false;

        int monthExpiry = Integer.parseInt(expiry.substring(0, 2));
        int yearExpiry = 2000 + Integer.parseInt(expiry.substring(2, 4));

        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);

        int monthRef = c.get(Calendar.MONTH) + 1; // months indexed from 0 in java
        int yearRef = c.get(Calendar.YEAR);

        if (yearExpiry < yearRef) {
            expired = true;
        } else if (yearExpiry == yearRef &&
                monthExpiry < monthRef) {
            expired = true;
        }

        return expired;
    }
}
