package com.gdn.training;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Number Helper Test")
class NumberHelperTest {
  @Test
  @DisplayName("is odd test")
  public void isOddTest() {
    Assertions.assertFalse(NumberHelper.isOdd(2));
    Assertions.assertTrue(NumberHelper.isOdd(3));
  }

  @Test
  @DisplayName("is even test")
  public void isEvenTest() {
    Assertions.assertFalse(NumberHelper.isEven(3));
    Assertions.assertTrue(NumberHelper.isEven(2));
  }
}