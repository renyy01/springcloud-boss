package com.camel.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 @author baily
 */
public class IoUtils {
    public static final Integer BYTE_ARRAY_SIZE = 1024;

    public static void writeToOtputStream(OutputStream outputStream, InputStream inputStream) throws IOException {
        byte[] b = new byte[BYTE_ARRAY_SIZE];
        int len;
        while ((len = inputStream.read(b, 0, BYTE_ARRAY_SIZE)) != -1) {
            outputStream.write(b, 0, len);
        }
    }
}
