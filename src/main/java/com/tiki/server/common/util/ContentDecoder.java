package com.tiki.server.common.util;

import java.util.Base64;
import java.util.List;

public class ContentDecoder {

    public static List<String> decodeNoteTemplate(final String encodedData) {
        String[] parts = encodedData.split("\\|");
        String decodedActivity = parts[0].isBlank() ? "" : new String(Base64.getDecoder().decode(parts[0]));
        String decodedPrepare = parts[1].isBlank() ? "" : new String(Base64.getDecoder().decode(parts[1]));
        String decodedDisappointing = parts[2].isBlank() ? "" : new String(Base64.getDecoder().decode(parts[2]));
        String decodedComplement = parts[3].isBlank() ? "" : new String(Base64.getDecoder().decode(parts[3]));
        return List.of(decodedActivity, decodedPrepare, decodedDisappointing, decodedComplement);
    }

    public static String decodeNoteFree(final String encodeDate) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodeDate);
        return new String(decodedBytes);
    }
}