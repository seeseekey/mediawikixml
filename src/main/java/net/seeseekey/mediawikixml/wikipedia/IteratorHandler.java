package net.seeseekey.mediawikixml.wikipedia;

public class IteratorHandler implements PageCallbackHandler {

    private WikiXMLParser parser = null;

    public IteratorHandler(WikiXMLParser parser) {
        this.parser = parser;
    }

    public void process(WikiPage page) {
        parser.notifyPage(page);
    }
}
