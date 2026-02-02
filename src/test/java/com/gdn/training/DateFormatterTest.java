package com.gdn.training;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {

    @Test
    void dateFormat() throws ParseException {
        DateFormatter formatter = new DateFormatter();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2023-06-01");
        String formattedDate = DateFormatter.getFormattedYearMonth(date);

        assertEquals("2023-06", formattedDate);
    }

    @Test
    void dateFormatNull() throws ParseException {
        DateFormatter formatter = new DateFormatter();
        Date date = null;
        assertThrows(IllegalArgumentException.class, () -> DateFormatter.getFormattedYearMonth(date));
    }
}