package com.gdn.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

class DateFormatterTest {

  @Test
  void testGetFormattedYearMonthWithValidDate() throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = sdf.parse("2023-01-01");
    String result = DateFormatter.getFormattedYearMonth(date);
    assertEquals("2023-01", result);
  }

  @Test
  void testGetFormattedYearMonthThrowsExceptionMessage() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      DateFormatter.getFormattedYearMonth(null);
    });
    assertEquals("date is null", exception.getMessage());
  }
}