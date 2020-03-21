package de.letsdev.products.dico.scanner.backend.helper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampConverter {

    private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static Timestamp convertStringToTimestamp(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_TIMESTAMP_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return Timestamp.valueOf(localDateTime);
    }
}
