package com.gdn.training;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateFormatterTest {
    @Test
    @DisplayName("date is null test")
    void dateIsNullTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> DateFormatter.getFormattedYearMonth(null));
    }

    @Test
    @DisplayName("date format success test")
    void dateFormatSuccessTest(){
        LocalDate today = LocalDate.now();
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        assertEquals(formattedDate,DateFormatter.getFormattedYearMonth(new Date()));
    }
}