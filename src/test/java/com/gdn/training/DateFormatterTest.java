package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Date Formatter Test")
class DateFormatterTest {

    @Test
    @DisplayName("get formatted year month")
    public void getFormattedYearMonth() {
        long epochDate = 1827157000000L;

    String result = DateFormatter.getFormattedYearMonth(new Date(epochDate));
    assertEquals("2027-11", result);

    }

    @Test
    @DisplayName("get formatted year month null")
    public void getFormattedYearMonthNull() {

        try {
            String result = DateFormatter.getFormattedYearMonth(null);
        } catch (Exception e){
            assertEquals("date is null", e.getMessage());
        }

    }

}