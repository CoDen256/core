package coden.multitran.context;

import coden.multitran.language.MultitranLanguage;

import java.util.stream.Stream;

/**
 * Represents an interface for fetching the context information from the multitran.com
 *
 * @author Denys Chernyshov
 */
public interface MultitranContextClient {
    /**
     * Fetches the context information for the given phrase from the multitran.com
     *
     * @param source
     *         the source language of the phrase
     * @param target
     *         the target language of the phrase
     * @param phrase
     *         the requested phrase to find context information for
     * @return the stream of contexts for the given phrase and given languages
     * @throws Exception
     *         if the fetching fails
     */
    Stream<MultitranContext> getContexts(MultitranLanguage source, MultitranLanguage target, String phrase) throws Exception;
}
