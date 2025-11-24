package com.gdn.training;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {

    @Test
    void getFormattedYearMonth() {
        DateFormatter.getFormattedYearMonth(new Date(1000));
        assertThrows(IllegalArgumentException.class,
                ()->DateFormatter.getFormattedYearMonth(null));
    }
}