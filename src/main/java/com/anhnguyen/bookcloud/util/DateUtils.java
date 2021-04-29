package com.anhnguyen.bookcloud.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateUtils {
    /**
     * Convert LocalDateTime to OffsetDateTime (using by API layer)
     * @param localDateTime
     * @return
     */
    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }

        ZoneOffset zoneOffSet = OffsetDateTime.now().getOffset();
        return localDateTime.atOffset(zoneOffSet);
    }
}
