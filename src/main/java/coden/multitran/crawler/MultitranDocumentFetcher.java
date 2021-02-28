package coden.multitran.crawler;

import coden.multitran.language.MultitranLanguage;
import org.jsoup.nodes.Document;

/**
 * Represents a document fetcher to obtain documents from multitran.com
 */
@FunctionalInterface
public interface MultitranDocumentFetcher {
    /**
     * Fetches the document for the given source, target language and requested phrase
     *
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @param phrase
     *         the requested phrase
     * @return the html document
     * @throws Exception
     *         if the loading of the document failed.
     */
    Document fetch(MultitranLanguage source, MultitranLanguage target, String phrase) throws Exception;
}
