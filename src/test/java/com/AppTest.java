package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    void testAddition() {
        int sum = 2 + 3;
        assertEquals(5, sum, "2 + 3 should equal 5");
    }

    @Test
    void testSubtraction() {
        int result = 10 - 5;
        assertEquals(5, result, "10 - 5 should equal 5");
}
}
