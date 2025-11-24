package com.gdn.training;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {
    @Test
    public void getFormattedYearMonthTest() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DateFormatter.getFormattedYearMonth(null);
        });

        Assertions.assertEquals(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1),
                DateFormatter.getFormattedYearMonth(date));
    }
}