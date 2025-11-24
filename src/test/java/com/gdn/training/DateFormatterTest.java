package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {

    @Test
    @DisplayName("Date Formatter")
    public void success() {
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.NOVEMBER, 24,0,0);
        Date date = cal.getTime();
        assertEquals("2025-11", DateFormatter.getFormattedYearMonth(date));
    }
}