package com.tiki.server.common.util;

import static com.tiki.server.common.exception.ErrorCode.EXCEEDED_MAX_LENGTH;

import com.ibm.icu.text.BreakIterator;
import com.tiki.server.common.exception.TikiException;

public class Validator {

    public static void validateLength(final String text, final int maxLength) {
        BreakIterator iterator = BreakIterator.getCharacterInstance();
        iterator.setText(text);

        int count = 0;
        while (BreakIterator.DONE != iterator.next()) {
            count++;
        }
        if(count > maxLength) {
            throw new TikiException(EXCEEDED_MAX_LENGTH);
        }
    }
}
