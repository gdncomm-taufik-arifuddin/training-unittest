package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class DateFormatterTest {
  @Test
  @DisplayName("Get Date Success")
  public void testGetDateSuccess() {
    Calendar calendar = new GregorianCalendar(2023, Calendar.OCTOBER, 25);
    Date inputDate = calendar.getTime();

    String result = DateFormatter.getFormattedYearMonth(inputDate);
    assertEquals("2023-10", result);
  }

  @Test
  @DisplayName("Get Date Failed")
  public void testGetDateFailed() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      DateFormatter.getFormattedYearMonth(null);
    });
    assertEquals("date is null", exception.getMessage());
  }
}
