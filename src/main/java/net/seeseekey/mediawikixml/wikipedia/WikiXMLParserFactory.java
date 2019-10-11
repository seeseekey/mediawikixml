package net.seeseekey.mediawikixml.wikipedia;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class WikiXMLParserFactory {

    private WikiXMLParserFactory() {}

    public static WikiXMLParser getParser(String fileName) throws MalformedURLException {
        return new WikiXMLSAXParser(new File(fileName).toURI().toURL());
    }

    public static WikiXMLParser getParser(String fileName, String languageCode) throws MalformedURLException {
        return new WikiXMLSAXParser(new File(fileName).toURI().toURL(), languageCode);
    }

    public static WikiXMLParser getParser(URL fileName) {
        return new WikiXMLSAXParser(fileName);
    }
}
