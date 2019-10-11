package net.seeseekey.mediawikixml;

import net.seeseekey.mediawikixml.wikipedia.PageCallbackHandler;
import net.seeseekey.mediawikixml.wikipedia.WikiPage;
import net.seeseekey.mediawikixml.wikipedia.WikiXMLParser;
import net.seeseekey.mediawikixml.wikipedia.WikiXMLParserFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class MediaWikiXmlTests {

    @Test
    @Disabled
    void manualTest() throws MalformedURLException {

        WikiXMLParser wikiXMLParser = WikiXMLParserFactory.getParser("dump-current.xml");

        try {

            wikiXMLParser.setPageCallback(new PageCallbackHandler() {
                public void process(WikiPage page) {
                    System.out.println(page.getId());
                    System.out.println(page.getRevisionId());
                    System.out.println(page.getTimestamp());
                    System.out.println(page.getTitle());
                    System.out.println(page.getWikiText());
                }
            });

            wikiXMLParser.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
