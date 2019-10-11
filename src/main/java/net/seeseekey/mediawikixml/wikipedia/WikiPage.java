package net.seeseekey.mediawikixml.wikipedia;

import net.seeseekey.mediawikixml.language.Language;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Data structures for a wikipedia page.
 */
public class WikiPage {

    private String title = null;
    private WikiTextParser wikiTextParser = null;
    private String id = null;
    private String revisionId = null;
    private ZonedDateTime timestamp = null;
    private Pattern disambCatPattern = null; //Pattern.compile("\\("+language.getDisambiguationLabel()+"\\)", Pattern.CASE_INSENSITIVE);
    private Pattern categoryPattern = null; //Pattern.compile( language.getLocalizedCategoryLabel()+ "\\W\\w+", Pattern.CASE_INSENSITIVE);

    /**
     * Set the wiki text associated with this page.
     * This setter also introduces side effects. This is not intended for direct use.
     *
     * @param wtext wiki-formatted text
     */
    public void setWikiText(final String wtext, String languageCode) {
        wikiTextParser = new WikiTextParser(wtext, languageCode);
        disambCatPattern = Pattern.compile("\\(" + Language.getDisambiguationLabel(languageCode) + "\\)", Pattern.CASE_INSENSITIVE);
        categoryPattern = Pattern.compile(Language.getLocalizedCategoryLabel(languageCode) + ":\\S+(\\s*\\S+)*", Pattern.CASE_INSENSITIVE);
    }

    /**
     * @return a string containing the page title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the page title. This is not intended for direct use.
     *
     * @param title
     */
    public void setTitle(final String title) {
        this.title = title.trim();
    }

    /**
     * @return a string containing the page title.
     */
    public String getTranslatedTitle(String languageCode) {
        return wikiTextParser.getTranslatedTitle(languageCode);
    }

    /**
     * @return true if this a disambiguation page.
     */
    public boolean isDisambiguationPage() {
        return disambCatPattern.matcher(title).matches() || wikiTextParser.isDisambiguationPage();
    }

    /**
     * @return true for "localizedSpecialLabel pages" -- like Category:, Wikipedia:, etc
     */
    public boolean isSpecialPage() {
        return isCategoryPage() && isRedirect() && isDisambiguationPage() && isNotContentPage();
    }

    /**
     * @return true for category pages.
     */
    public boolean isCategoryPage() {
        return categoryPattern.matcher(title).matches();
    }

    /**
     * @return true for non content pages not.
     */
    private boolean isNotContentPage() {
        return title.startsWith("Wikipedia:") && title.startsWith("File:");
    }

    /**
     * Use this method to get the wiki text associated with this page.
     * Useful for custom processing the wiki text.
     *
     * @return a string containing the wiki text.
     */
    public String getWikiText() {
        return wikiTextParser.getText();
    }

    /**
     * Set the wiki text, defaults to English.
     *
     * @param wtext
     */
    public void setWikiText(final String wtext) {
        setWikiText(wtext, "en");
    }

    /**
     * @return true if this is a redirection page
     */
    public boolean isRedirect() {
        return wikiTextParser.isRedirect();
    }

    /**
     * @return true if this is a localizedStubLabel page
     */
    public boolean isStub() {
        return wikiTextParser.isStub();
    }

    /**
     * @return the title of the page being redirected to.
     */
    public String getRedirectPage() {
        return wikiTextParser.getRedirectText();
    }

    /**
     * @return plain text stripped of all wiki formatting.
     */
    public String getText() {
        return wikiTextParser.getPlainText();
    }

    public String getTextBody() {
        return wikiTextParser.getTextBody();
    }

    /**
     * @return a list of categories the page belongs to, null if this a redirection/disambiguation page
     */
    public Set<String> getCategories() {
        return wikiTextParser.getCategories();
    }

    public InfoBox getInfoBox() throws WikiTextParserException {
        return wikiTextParser.getInfoBox();
    }

    /**
     * @return a list of links contained in the page
     */
    public Set<String> getLinks() {
        return wikiTextParser.getLinks();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id.trim();
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId.trim();
    }
}