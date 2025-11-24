package com.gdn.training;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Date Formatter Test")
class DateFormatterTest {

    @Test
    @DisplayName("date formatter test")
    public void successFormatterTest() {
        Date date = new Date();
        date.setTime(1234567890);

        assertEquals("1970-01",DateFormatter.getFormattedYearMonth(date));
    }

    @Test
    public void nullFormatterTest() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                DateFormatter.getFormattedYearMonth(null)
        );

        assertEquals("date is null", ex.getMessage());
    }
}
