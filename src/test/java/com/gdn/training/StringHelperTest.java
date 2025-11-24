package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("String Helper Test")
class StringHelperTest {
    @Test
    @DisplayName("success convert test")
    void successConvertTest() {
        List<String> listTestString = new ArrayList<>();
        listTestString.add("test");
        listTestString.add("aiueo");
        listTestString.add("stonk");
        listTestString.add(null);

        List<String> resultTest = StringHelper.toUpperCase(listTestString);
        assertEquals(3, resultTest.size());
        for (String value : resultTest) {
            assertEquals(value,value.toUpperCase());
        }
    }
}