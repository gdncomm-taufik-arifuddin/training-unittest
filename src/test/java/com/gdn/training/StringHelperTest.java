package com.gdn.training;

import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("String Helper Test")
class StringHelperTest {

  @Test
  @DisplayName("Should convert all strings to uppercase successfully")
  public void toUpperCaseTest() {
    List<String> stringList = new ArrayList<>();
    stringList.add("nospace");
    stringList.add("with space");
    stringList.add(null);

    List<String> result = StringHelper.toUpperCase(stringList);
    assertEquals(2, result.size());

    assertEquals("NOSPACE", result.get(0));
    assertEquals("WITH SPACE", result.get(1));


  }

}