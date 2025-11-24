package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DateFormatterTest {
  @Test
  @DisplayName("is null date test")
  public void isNullDateTest() {
    try {
      DateFormatter.getFormattedYearMonth(null);
    } catch (IllegalArgumentException e) {
      assert (e.getMessage().equals("date is null"));
    }
  }

  @Test
  @DisplayName("is format date test")
  public void isFormatDateTest() {
    Date date = new Date("2023/08/15");
    String dateFormat = DateFormatter.getFormattedYearMonth(date);
    assertTrue(dateFormat.equals("2023-08"));
  }

}