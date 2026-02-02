package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringHelperTest {

    @Test
    @DisplayName("String Helper")
    public void success() {
        List<String> strings = new ArrayList<>();
        strings.add("aa");
        strings.add("bb");
        strings.add(null);
        strings.add("cc");

        List<String> result = StringHelper.toUpperCase(strings);
//        System.out.println(result);

        //verify filter
        assertEquals(3, result.size());

        //verify each index
//        for (int i = 0; i < result.size(); i++) {
//            assertEquals(strings.get(i).toUpperCase(), result.get(i));
//        }

        assertEquals("AA", result.get(0));
        assertEquals("BB", result.get(1));
        assertEquals("CC", result.get(2));
    }
}