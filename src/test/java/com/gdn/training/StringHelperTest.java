package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("String Helper Test")
class StringHelperTest {

  @Test
  @DisplayName("toUpperCase should convert all non-null strings to upper case")
  void toUpperCase_basicConversion() {
    List<String> input = Arrays.asList("hello", "World");
    List<String> result = StringHelper.toUpperCase(input);

    assertEquals(Arrays.asList("HELLO", "WORLD"), result);
  }

  @Test
  @DisplayName("toUpperCase should ignore null values")
  void toUpperCase_ignoresNullValues() {
    List<String> input = Arrays.asList("hello", null, "world", null);
    List<String> result = StringHelper.toUpperCase(input);

    assertEquals(Arrays.asList("HELLO", "WORLD"), result);
    assertEquals(2, result.size());
  }

  @Test
  @DisplayName("toUpperCase should return empty list when input is empty")
  void toUpperCase_emptyList_returnsEmptyList() {
    List<String> input = Collections.emptyList();
    List<String> result = StringHelper.toUpperCase(input);

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }
}
