package com.gdn.training;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {

    @Test
    public void testStringHelper() {
        List<String> strings = new ArrayList<>(5);
        strings.add("aa");
        strings.add("bb");
        strings.add("cc");


        List<String> results =StringHelper.toUpperCase(strings);
        assertEquals(3, results.size());

        for(int i = 0; i < results.size(); i++){
            assertEquals(strings.get(i).toUpperCase(), results.get(i));
        }
    }
}