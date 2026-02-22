package com.eduardo.urlshortener.utils;

import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALLOWED_CHARACTERS.length();

    public String encode(long input) {
        StringBuilder encodedString = new StringBuilder();

        if (input == 0) return String.valueOf(ALLOWED_CHARACTERS.charAt(0));

        //base 10 para base 62
        while (input > 0) {
            encodedString.append(ALLOWED_CHARACTERS.charAt((int)(input % BASE)));
            input /= BASE;
        }
        return encodedString.toString();
    }
}
