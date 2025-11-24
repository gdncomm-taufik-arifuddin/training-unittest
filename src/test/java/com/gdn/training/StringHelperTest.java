package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("String helper test")
class StringHelperTest {
  @Test
  public void testNotNull() {
    List<String> list = new ArrayList<>();
    list.add("abc");
    list.add("cde");
    List<String> result = StringHelper.toUpperCase(list);
    assertEquals(result.size(), list.size());
    for (int i = 0; i < result.size(); i++) {
      assertEquals(result.get(i), result.get(i).toUpperCase());
    }
  }

  @Test
  public void testWithNull() {
    List<String> list = new ArrayList<>();
    list.add("abc");
    list.add(null);
    list.add("ced");
    List<String> result = StringHelper.toUpperCase(list);
    assertEquals(result.size(), 2);
    assertEquals(result,
        list.stream().filter(a -> a != null)
            .map(a -> a.toUpperCase()).collect(Collectors.toList()));
  }
}