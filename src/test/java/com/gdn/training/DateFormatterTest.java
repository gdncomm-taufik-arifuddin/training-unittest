package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Date Formatter Test")
class DateFormatterTest {

  @Test
  @DisplayName("getFormattedYearMonth should return yyyy-MM format")
  void getFormattedYearMonth_validDate_returnsFormattedString() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2025, Calendar.NOVEMBER, 24); // month is 0-based
    Date date = calendar.getTime();

    String result = DateFormatter.getFormattedYearMonth(date);

    assertEquals("2025-11", result);
  }

  @Test
  @DisplayName("getFormattedYearMonth should throw IllegalArgumentException when date is null")
  void getFormattedYearMonth_null_throwsException() {
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class, () -> DateFormatter.getFormattedYearMonth(null));

    assertEquals("date is null", ex.getMessage());
  }
}
