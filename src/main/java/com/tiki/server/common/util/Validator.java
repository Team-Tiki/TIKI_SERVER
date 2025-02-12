package com.tiki.server.common.util;

import static com.tiki.server.common.exception.ErrorCode.EXCEEDED_MAX_LENGTH;
import static com.tiki.server.common.exception.ErrorCode.INVALID_CHARACTER;

import com.ibm.icu.text.BreakIterator;
import com.tiki.server.common.exception.TikiException;

public class Validator {

    public static void validateLengthContainEmoji(final String text, final int maxLength) {
        BreakIterator iterator = BreakIterator.getCharacterInstance();
        iterator.setText(text);

        int count = 0;
        while (BreakIterator.DONE != iterator.next()) {
            count++;
        }
        if (count > maxLength) {
            throw new TikiException(EXCEEDED_MAX_LENGTH);
        }
    }

    public static void validateLength(final String text, final int maxLength) {
        if (text.length() > maxLength) {
            throw new TikiException(EXCEEDED_MAX_LENGTH);
        }
    }

    public static void validText(final String text) {
        String regex = "^[a-zA-Z가-힣0-9 !@#$%^&*()\\-_=+\\[\\]{};:'\",.<>?/|\\\\]+$";
        if (!text.matches(regex)) {
            throw new TikiException(INVALID_CHARACTER);
        }
    }
}
