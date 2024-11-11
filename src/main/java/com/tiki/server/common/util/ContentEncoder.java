package com.tiki.server.common.util;

import java.util.Base64;

public class ContentEncoder {

    public static String encodeNoteTemplate(final String activity, final String prepare, final String disappointing, final String complement) {
        String encodedActivity = Base64.getEncoder().encodeToString(activity.getBytes());
        String encodedPrepare = Base64.getEncoder().encodeToString(prepare.getBytes());
        String encodedDisappointing = Base64.getEncoder().encodeToString(disappointing.getBytes());
        String encodedComplement = Base64.getEncoder().encodeToString(complement.getBytes());

        return String.join("|", encodedActivity, encodedPrepare, encodedDisappointing, encodedComplement);
    }

    public static String encodeNoteFree(final String contents) {
        return Base64.getEncoder().encodeToString(contents.getBytes());
    }
}