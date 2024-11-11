package com.tiki.server.common.util;

import java.util.Base64;

public class ContentDecoder {

    public static String encodeNoteFree(final String contents) {
        byte[] decodedBytes = Base64.getDecoder().decode(contents);
        return new String(decodedBytes);
    }
}