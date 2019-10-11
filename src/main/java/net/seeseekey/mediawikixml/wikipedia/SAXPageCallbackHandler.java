package net.seeseekey.mediawikixml.wikipedia;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.ZonedDateTime;

/**
 * A Wrapper class for the PageCallbackHandler
 */
public class SAXPageCallbackHandler extends DefaultHandler {

    private boolean insideRevision = false;
    private boolean insideContributor = false;

    private PageCallbackHandler pageHandler;
    private WikiPage currentPage;
    private String currentTag;

    private StringBuilder currentWikitext;
    private StringBuilder currentTitle;
    private StringBuilder currentID;
    private StringBuilder currentRevisionID;
    private StringBuilder currentTimestamp;
    private String language = null;

    public SAXPageCallbackHandler(PageCallbackHandler pageHandler, String language) {
        this.pageHandler = pageHandler;
        this.language = language;
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes attr) {

        currentTag = qName;

        if (qName.equals("page")) {
            currentPage = new WikiPage();
            currentWikitext = new StringBuilder("");
            currentTitle = new StringBuilder("");
            currentID = new StringBuilder("");
            currentRevisionID = new StringBuilder("");
            currentTimestamp = new StringBuilder("");
        }

        if (qName.equals("revision")) {
            insideRevision = true;
        }

        if (qName.equals("contributor")) {
            insideContributor = true;
        }
    }

    @Override
    public void endElement(String uri, String name, String qName) {

        if (qName.equals("revision")) {
            insideRevision = false;
        }

        if (qName.equals("contributor")) {
            insideContributor = false;
        }

        if (qName.equals("page")) {
            currentPage.setTitle(currentTitle.toString());
            currentPage.setId(currentID.toString());
            currentPage.setRevisionId(currentRevisionID.toString());
            currentPage.setTimestamp(ZonedDateTime.parse(currentTimestamp.toString().trim()));
            currentPage.setWikiText(currentWikitext.toString(), language);
            pageHandler.process(currentPage);
        }

        if (qName.equals("mediawiki")) {
            // TODO hasMoreElements() should now return false
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (currentTag.equals("title")) {
            currentTitle.append(ch, start, length);
        } else if (currentTag.equals("timestamp")) {
            currentTimestamp.append(ch, start, length);
        } else if ((currentTag.equals("id"))) {

            if (insideRevision) {
                if (!insideContributor) {
                    currentRevisionID.append(ch, start, length);
                }
            } else {
                currentID.append(ch, start, length);
            }

        } else if (currentTag.equals("text")) {
            currentWikitext.append(ch, start, length);
        }
    }
}