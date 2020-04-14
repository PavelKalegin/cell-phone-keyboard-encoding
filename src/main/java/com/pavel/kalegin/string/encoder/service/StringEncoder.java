package com.pavel.kalegin.string.encoder.service;

import org.springframework.lang.NonNull;

public interface StringEncoder {
    /**
     * Encode given string.
     * @param input string to be encoded
     * @return encoded string.
     */
    @NonNull
    String encode (@NonNull String input);
}
