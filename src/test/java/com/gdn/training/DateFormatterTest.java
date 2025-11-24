package com.gdn.training;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Date Formatter Test")
public class DateFormatterTest {
    @Test
    public void successDateFormatterTest() {
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JANUARY, 15, 0, 0, 0);
        Date date = cal.getTime();
        Assertions.assertEquals("2025-01", DateFormatter.getFormattedYearMonth(date));
    }

    @Test
    public void  nullDateFormatterTest() {
        IllegalArgumentException dateNull = assertThrows(
                IllegalArgumentException.class,
                () -> DateFormatter.getFormattedYearMonth(null)
        );
        Assertions.assertEquals("date is null", dateNull.getMessage());
    }
}
