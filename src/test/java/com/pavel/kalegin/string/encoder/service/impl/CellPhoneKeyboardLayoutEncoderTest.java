package com.pavel.kalegin.string.encoder.service.impl;


import com.pavel.kalegin.string.encoder.service.StringEncoder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CellPhoneKeyboardLayoutEncoderTest {

    private static final Map<Integer, String[]> KEY_PAD = new HashMap<>(10) {
        {
            put(2, new String[]{"a", "b", "c"});
            put(3, new String[]{"d", "e", "f"});
            put(4, new String[]{"g", "h", "i"});
            put(5, new String[]{"j", "k", "l"});
            put(6, new String[]{"m", "n", "o"});
            put(7, new String[]{"p", "q", "r", "s"});
            put(8, new String[]{"t", "u", "v"});
            put(9, new String[]{"w", "x", "y", "z"});
            put(0, new String[]{" "});
        }
    };

    private final StringEncoder stringEncoder = new CellPhoneKeyboardLayoutEncoder(KEY_PAD);

    @ParameterizedTest
    @CsvSource(value =
            {
                    "Two same chars from one group split:aa:2 2",
                    "Two different chars from one group split:ab:2 22",
                    "Two chars from different groups are together:dg:34",
                    "Single word test case:hi:44 444",
                    "Two same chars in the middle:foo  bar:333666 6660 022 2777"
            },
            delimiter = ':')
    public void encode(String testCase, String input, String expected) {
        assertEquals(testCase, expected, stringEncoder.encode(input));
    }


}