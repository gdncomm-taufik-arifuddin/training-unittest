package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("String Helper Test")
class StringHelperTest {
  @Test
  @DisplayName("is uppercase test")
  public void isUpperCaseTest() {
    List<String> input = Arrays.asList("hello", "world", "java");
    List<String> result = StringHelper.toUpperCase(input);
    assertFalse(result.isEmpty());
    assertTrue(result.containsAll(Arrays.asList("HELLO", "WORLD", "JAVA")));
    assertTrue(result.size() == 3);
  }

  @Test
  @DisplayName("is contains empty test")
  public void isContainsEmptyTest() {
    List<String> input = Arrays.asList("hello", "world", null, "java");
    List<String> result = StringHelper.toUpperCase(input);
    assertFalse(result.isEmpty());
    assertTrue(result.equals(Arrays.asList("HELLO", "WORLD", "JAVA")));
    assertTrue(!result.equals(Arrays.asList("HELLO", "WORLD", null, "JAVA")));
  }

  @Test
  @DisplayName("is empty list test")
  public void isNullTest() {
    List<String> input = Arrays.asList();
    List<String> result = StringHelper.toUpperCase(input);
    assertTrue(result.isEmpty());
  }
}