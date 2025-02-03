package com.tiki.server.common.util;

import static com.tiki.server.common.exception.ErrorCode.EMOJI_NOT_ALLOWED;
import static com.tiki.server.common.exception.ErrorCode.EXCEEDED_MAX_LENGTH;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.lang.UProperty;
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
        if(count > maxLength) {
            throw new TikiException(EXCEEDED_MAX_LENGTH);
        }
    }

    public static void validateLength(final String text, final int maxLength) {
        BreakIterator iterator = BreakIterator.getCharacterInstance();
        iterator.setText(text);
        int count = 0;
        int index = iterator.first();
        while (index != BreakIterator.DONE) {
            int codePoint = text.codePointAt(index);
            if (UCharacter.hasBinaryProperty(codePoint, UProperty.EMOJI)) {
                throw new TikiException(EMOJI_NOT_ALLOWED);
            }
            count++;
            if (count > maxLength) {
                throw new TikiException(EXCEEDED_MAX_LENGTH);
            }
            index = iterator.next();
        }
    }
}
