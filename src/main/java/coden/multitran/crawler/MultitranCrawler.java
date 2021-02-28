package coden.multitran.crawler;

import coden.multitran.language.MultitranLanguage;
import coden.multitran.translation.MultitranTranslation;
import coden.multitran.translation.MultitranTranslationClient;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * The Crawler of the multitran.com to fetch information from the web page.
 *
 * @author Denys Chernyshov
 */
public class MultitranCrawler implements MultitranTranslationClient {

    private static final Pattern queryStartPattern = Pattern.compile("^/m.exe\\?.*");
    private static final Pattern queryWordSearchPattern = Pattern.compile(".*&?s=[^&]*.*");
    private static final String queryLanguagePattern = ".*&?%s=%s.*";

    /** The Document Fetcher to parse */
    private final MultitranDocumentFetcher fetcher;

    public MultitranCrawler(MultitranDocumentFetcher fetcher) {
        this.fetcher = fetcher;
    }

    @Override
    public Stream<MultitranTranslation> getTranslations(MultitranLanguage source, MultitranLanguage target, String phrase) throws Exception {
        Document document = fetcher.fetch(source, target, phrase);
        return parseDocumentTranslations(document, source, target);
    }

    /**
     * Fetches all the multitran translations for the given page by scanning page for certain
     * tags and especially accounting links, which certainly represent the actual translation
     *
     * @param document
     *         the document to scan and to parse
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @return stream of parsed translations
     */
    private Stream<MultitranTranslation> parseDocumentTranslations(Document document, MultitranLanguage source, MultitranLanguage target) {
        return document.getElementsByClass("trans")
                .stream()
                .map(el -> el.getElementsByAttribute("href"))
                .flatMap(Elements::stream)
                .filter(el -> matchesTranslationHref(el.attr("href"), target, source))
                .map(Element::text)
                .map(MultitranTranslation::new);
    }

    /**
     * Tells whether the given link represents the actual link for the word and not for
     * any other entity (for example link to author). The link to words that represent translation
     * contains of course the reversed direction of translation
     *
     * @param href
     *         the link to check
     * @param l1
     *         the source language for the translation, thus with reversed direction - target language
     * @param l2
     *         the target language for the translation, thus with reversed direction - target language
     * @return {@code true} if it is link to translation {@code false} otherwise
     */
    private boolean matchesTranslationHref(String href, MultitranLanguage l1, MultitranLanguage l2) {
        return matchesQueryStart(href) &&
                matchesQueryWordSearch(href) &&
                matchesQueryLanguage(href, "l1", l1) &&
                matchesQueryLanguage(href, "l2", l2);
    }

    private boolean matchesQueryStart(String href) {
        return queryStartPattern.matcher(href).matches();
    }

    private boolean matchesQueryWordSearch(String href) {
        return queryWordSearchPattern.matcher(href).matches();
    }

    private boolean matchesQueryLanguage(String href, String languageType, MultitranLanguage lang) {
        String languageRegex = String.format(queryLanguagePattern, languageType, lang.getMultitranStandard());
        return href.matches(languageRegex);
    }
}
