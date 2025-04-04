package io.github.coden256.reverso.client.crawler;

import io.github.coden256.reverso.language.ReversoLanguage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Represents an interface for fetching the html documents using {@link Jsoup}
 *
 * @author Denys Chernyshov
 */
@FunctionalInterface
public interface ReversoDocumentFetcher {
    /**
     * Fetches the document for the page from reverso.net
     *
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @param phrase
     *         the phrase to be requested
     * @return the html document
     * @throws IOException
     *         while fetching
     */
    Document fetch(ReversoLanguage source, ReversoLanguage target, String phrase) throws IOException;
}
