package com.gdn.training;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("String Helper Test")
class StringHelperTest {
    @Test
    @DisplayName("to upper case test")
    public void toUpperCaseTest() {
        List<String> strings = new ArrayList<>();
        strings.add("abc");
        strings.add("DEF");
        strings.add("GhI");
        strings.add(null);

        List<String> result = StringHelper.toUpperCase(strings);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("ABC", result.get(0));
        Assertions.assertEquals("DEF", result.get(1));
        Assertions.assertEquals("GHI", result.get(2));
    }

}