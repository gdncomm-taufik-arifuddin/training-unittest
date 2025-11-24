package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateFormatterTest {

  @Test
  @DisplayName("date null")
  public void dateNullTest() {
    Date date = null;
    assertThrows(IllegalArgumentException.class, () -> DateFormatter.getFormattedYearMonth(date));
  }

  @Test
  @DisplayName("format date")
  public void formatDateTest() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2025, Calendar.JANUARY, 1);
    Date date = calendar.getTime();
    assertEquals(DateFormatter.getFormattedYearMonth(date),"2025-01");
  }

}