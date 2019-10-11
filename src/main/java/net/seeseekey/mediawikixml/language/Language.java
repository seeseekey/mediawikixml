package net.seeseekey.mediawikixml.language;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.seeseekey.mediawikixml.utils.ResourceUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for language specific features
 */
public class Language {

    private Language() {
    }

    private static Map<String, LanguageEntry> languageEntries;

    static {
        Gson gson = new Gson();
        String json = null;

        try {
            json = ResourceUtils.getResource("languages.json");
        } catch (IOException e) {
            languageEntries = new HashMap<>();
        }

        if (languageEntries == null) {
            Type type = new TypeToken<Map<String, LanguageEntry>>() {}.getType();
            languageEntries = gson.fromJson(json, type);
        }
    }

    public static String getLocalizedCategoryLabel(String languageCode) {
        return languageEntries.get(languageCode).getCategory();
    }

    public static String getLocalizedSpecialLabel(String languageCode) {
        return languageEntries.get(languageCode).getSpecial();
    }

    public static String getLocalizedPortalLabel(String languageCode) {
        return languageEntries.get(languageCode).getPortal();
    }

    public static String getLocalizedRedirectLabel(String languageCode) {
        return languageEntries.get(languageCode).getRedirect();
    }

    public static String getLocalizedStubLabel(String languageCode) {
        return languageEntries.get(languageCode).getStub();
    }

    public static String getDisambiguationLabel(String languageCode) {
        return languageEntries.get(languageCode).getDisambiguation();
    }

    public static String getSeeAlsoLabel(String languageCode) {
        return languageEntries.get(languageCode).getSeealso();
    }

    public static String getFurtherLabel(String languageCode) {
        return languageEntries.get(languageCode).getFurther();
    }

    public static String getReferencesLabel(String languageCode) {
        return languageEntries.get(languageCode).getReferences();
    }

    public static String getNotesLabel(String languageCode) {
        return languageEntries.get(languageCode).getNotes();
    }
}
