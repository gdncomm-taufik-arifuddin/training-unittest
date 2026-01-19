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

    @Test
    @DisplayName("success to format date")
    public void successFormatDate() {
        //1768795712000 19 jan
        assertEquals("2026-01", DateFormatter.getFormattedYearMonth(new Date(1768795712000l)));
    }

    @Test
    @DisplayName("get message when null")
    public void nullFormattedDateTest() {
        IllegalArgumentException illegalArgumentException =
        assertThrows(IllegalArgumentException.class, () -> DateFormatter.getFormattedYearMonth(null));
        assertEquals("date is null", illegalArgumentException.getMessage());
    }
}