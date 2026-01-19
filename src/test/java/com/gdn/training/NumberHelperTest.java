package com.gdn.training;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Number Helper Test")
class NumberHelperTest {
    @Test
    @DisplayName("is odd test")
    public void isOddTest() {
        assertFalse(NumberHelper.isOdd(2));
        assertTrue(NumberHelper.isOdd(3));
        assertFalse(NumberHelper.isOdd(100*2/3));
        assertTrue(NumberHelper.isOdd(9/3));

        assertFalse(NumberHelper.isEven(123));
        assertTrue(NumberHelper.isEven(100));
        assertFalse(NumberHelper.isEven(150-3));
        assertTrue(NumberHelper.isEven(100-48));

    }
}