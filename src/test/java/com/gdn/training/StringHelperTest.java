package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("String Helper Test")
class StringHelperTest {
    @Test
    @DisplayName("success convert")
    public void successConvert() {
        List<String> strings = new ArrayList<>();
        strings.add("data1");
        strings.add("data2");
        strings.add(null);

        List<String> result = StringHelper.toUpperCase(strings);
        assertEquals(2, result.size());
        assertEquals("DATA1", result.get(0));
        assertEquals("DATA2", result.get(1));
    }

}