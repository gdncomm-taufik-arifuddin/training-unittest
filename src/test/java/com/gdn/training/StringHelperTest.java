package com.gdn.training;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StringHelperTest {
  @Test
  public void successConvert(){
    List<String> strings = new ArrayList<>();
    strings.add("aa");
    strings.add("bb");
    strings.add("cc");

    List<String> result = StringHelper.toUpperCase(strings);

    assertEquals("AA", result.get(0));
    assertEquals("BB", result.get(1));
    assertEquals("CC", result.get(2));
  }
}
