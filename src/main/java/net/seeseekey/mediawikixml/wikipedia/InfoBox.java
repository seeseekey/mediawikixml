package net.seeseekey.mediawikixml.wikipedia;

/**
 * A class abstracting Wiki infobox
 */
public class InfoBox {

    String infoBoxWikiText;

    InfoBox(String infoBoxWikiText) {

        if (infoBoxWikiText != null) {
            this.infoBoxWikiText = infoBoxWikiText;
        } else {
            // set infobox text to empty string
            this.infoBoxWikiText = "";
        }
    }

    public String dumpRaw() {
        return infoBoxWikiText;
    }

    public boolean isEmpty() {
        return infoBoxWikiText.isEmpty();
    }
}
