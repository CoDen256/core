package coden.reverso.website;

import coden.reverso.language.ReversoLanguage;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

/**
 * The {@link ReversoUrls} represents utility class to store all urls used by reverso. Including
 * real website's urls as well as api endpoints.
 * reverso.net uses two different api approaches:
 * 1) Real API, that is used to obtain translation, but as side effect, context can be obtained as well
 * 2) BST Query Service used only to fetch context.
 * <p>
 * Note: no need to let links be added via configuration file from the outside, because the links itself and
 * the approaches are ambiguous, so the the client just have to use already  implemented clients, that hide
 * the actual links and requests.
 *
 * @author Denys Chernyshov
 */
public final class ReversoUrls {

    public static final String CONTEXT_URL_PATTERN = "https://context.reverso.net/translation/{source}-{target}/{phrase}";
    public static final String TRANSLATION_URL_PATTERN = "https://www.reverso.net/translationresults.aspx?lang=EN&direction={source}-{target}";
    public static final String QUERY_SERVICE_ENDPOINT = "https://context.reverso.net/bst-query-service";
    public static final String TRANSLATE_ENDPOINT = "https://api.reverso.net/translate/v1/translation";

    private ReversoUrls() {
    }

    /**
     * Returns the url directing to the website, where user can see actual translations for the
     * given languages and phrase.
     *
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @param phrase
     *         the phrase which is requested
     * @return the url to find user-friendly results
     */
    public static String getContextUrl(ReversoLanguage source, ReversoLanguage target, String phrase) {
        return StringSubstitutor.replace(CONTEXT_URL_PATTERN, Map.of(
                "phrase", phrase,
                "source", source.getUrlStandard(),
                "target", target.getUrlStandard()), "{", "}");
    }

    /**
     * Returns the translation url directing to the website with user friendly results for requested
     * phrase.
     * Unlike context url, translation url cannot be requested for particular phrase, so this url just points
     * to translation service in general and not for the particular translation of the given word
     *
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @param phrase
     *         the requested phrase
     * @return the translation url pointing to website of translation
     */
    public static String getTranslationUrl(ReversoLanguage source, ReversoLanguage target, String phrase) {
        return StringSubstitutor.replace(TRANSLATION_URL_PATTERN, Map.of(
                "source", source.getUrlStandard(),
                "target", target.getUrlStandard()), "{", "}");
    }
}
