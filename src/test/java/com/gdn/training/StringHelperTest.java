package com.gdn.training;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringHelperTest {

    @Test
    void toUpperCase() {
        List<String> testList = new ArrayList<String>();
        testList.add("string1");
        testList.add("string2");
        testList.add(null);
        List<String> resultList;
        resultList = StringHelper.toUpperCase(testList);
        assertEquals(2, resultList.size());
        assertEquals("STRING1", resultList.get(0));
        assertEquals("STRING2", resultList.get(1));

    }
}