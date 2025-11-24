package com.gdn.training;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateFormatterTest {
  @Test
  public void successFormatDate() {
    Date now = new Date();
    String expected = new SimpleDateFormat("yyyy-MM").format(now);
    String result = DateFormatter.getFormattedYearMonth(now);
    assertEquals(expected, result);
  }

  @Test
  public void dateIsNull() {
    assertThrows(IllegalArgumentException.class, () -> DateFormatter.getFormattedYearMonth(null));
  }
}