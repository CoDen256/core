package coden.multitran.website;

import coden.multitran.language.MultitranLanguage;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

/**
 * The {@link MultitranUrls} represents utility class to store all urls used by multitran.com
 *
 * @author Denys Chernyshov
 */
public final class MultitranUrls {

    private static final String TRANSLATION_URL_PATTERN = "https://www.multitran.com/m.exe?s={phrase}&l1={source}&l2={target}";

    private MultitranUrls(){}

    /**
     * Returns the translation url directing to the website with user friendly results for requested
     * phrase.
     *
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @param phrase
     *         the requested phrase
     * @return the translation url pointing to website of translation
     */
    public static String getTranslationUrl(MultitranLanguage source, MultitranLanguage target, String phrase) {
        return StringSubstitutor.replace(TRANSLATION_URL_PATTERN, Map.of(
                "source", source.getMultitranStandard(),
                "target", target.getMultitranStandard(),
                "phrase", phrase),
                "{", "}");
    }
}
