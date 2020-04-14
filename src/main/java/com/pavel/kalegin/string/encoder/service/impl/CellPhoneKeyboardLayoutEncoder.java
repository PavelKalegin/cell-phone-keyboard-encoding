package com.pavel.kalegin.string.encoder.service.impl;

import com.pavel.kalegin.string.encoder.service.StringEncoder;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link StringEncoder} which uses cell phone keyboard layout.
 * By default provided Latin alphabet with whitespace.
 */
public class CellPhoneKeyboardLayoutEncoder implements StringEncoder {
    public static final Map<Integer, String[]> DEFAULT_LATIN_KEYPAD = new HashMap<>(10) {
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

    @NonNull
    private final Map<Character, String> charCodes;

    public CellPhoneKeyboardLayoutEncoder(@NonNull Map<Integer, String[]> keyPad) {
        charCodes = new HashMap<>();
        keyPad.forEach((key, value) -> {
            for (int i = 0; i < value.length; i++) {
                this.charCodes.put(value[i].charAt(0), key.toString().repeat(i + 1));
            }
        });
    }

    public CellPhoneKeyboardLayoutEncoder() {
        this(DEFAULT_LATIN_KEYPAD);
    }

    @Override
    public String encode(@NonNull String input) {
        return input.chars()
                .mapToObj(c -> charCodes.get((char) c))
                .reduce((current, next) ->
                        current += current.charAt(current.length() - 1) == next.charAt(0) ? " " + next : next)
                .orElse("");
    }
}
