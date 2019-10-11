package net.seeseekey.mediawikixml.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResourceUtils {

    private ResourceUtils() {
    }

    public static String getResource(String resource) throws IOException {

        URL url = com.google.common.io.Resources.getResource(resource);
        try {
            return com.google.common.io.Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }
}
