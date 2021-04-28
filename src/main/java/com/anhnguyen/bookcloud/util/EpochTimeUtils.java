package com.anhnguyen.bookcloud.util;

import java.util.Date;

public class EpochTimeUtils {
    public static Long toEpochTime(Date date) {
        if (date == null) {
            return null;
        }

        return date.getTime();
    }
}
