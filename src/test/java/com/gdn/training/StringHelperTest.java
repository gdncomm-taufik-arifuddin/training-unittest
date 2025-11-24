package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("String Helper Test")
class StringHelperTest {

  @Test
  @DisplayName("to UpperCase value Mix Case")
  public void isToUpperTestMixCase() {
    //Input Step
    List<String> input = new ArrayList<>();
    input.add("test");
    input.add(null);
    input.add("handi");
    input.add("BUDI");
    //Action Method Call
    List<String> output = StringHelper.toUpperCase(input);
    //Remove null from input list
    input = input.stream().filter(s -> s != null).toList();
    //Assertion Step
    //Size input(exclude null) size == output size
    assertEquals(input.size(),output.size());
    //assert all input except null must be on output
    for (String name: input)
    {
      assertTrue(output.contains(name.toUpperCase()));
    }
  }

  @Test
  @DisplayName("to UpperCase value All Null Case")
  public void isToUpperTestAllNullCase() {
    //Input Step
    List<String> input = new ArrayList<>();
    input.add(null);
    input.add(null);
    input.add(null);
    //Action Method Call
    List<String> output = StringHelper.toUpperCase(input);
    //Remove null from input list
    input = input.stream().filter(s -> s != null).toList();
    //Assertion Step
    //Size input(exclude null) size == output size
    assertEquals(input.size(),output.size());
    //assert all input except null must be on output
    for (String name: input)
    {
      assertTrue(output.contains(name.toUpperCase()));
    }
  }

  @Test
  @DisplayName("to UpperCase value All Not Null Case")
  public void isToUpperTestAllNotNullCase() {
    //Input Step
    List<String> input = new ArrayList<>();
    input.add("handi");
    input.add("lieputra");
    input.add("testing");
    //Action Method Call
    List<String> output = StringHelper.toUpperCase(input);
    //Remove null from input list
    input = input.stream().filter(s -> s != null).toList();
    //Assertion Step
    //Size input(exclude null) size == output size
    assertEquals(input.size(),output.size());
    //assert all input except null must be on output
    for (String name: input)
    {
      assertTrue(output.contains(name.toUpperCase()));
    }
  }
}