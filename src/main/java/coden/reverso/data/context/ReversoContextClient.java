package coden.reverso.data.context;

import coden.reverso.language.ReversoLanguage;

import java.util.stream.Stream;

/**
 * Represents a client to fetch Contexts from the reverso.net
 *
 * @author Denys Chernyshov
 * @see ReversoContext
 */
public interface ReversoContextClient {
    /**
     * Returns a stream of {@link ReversoContext}s fetched from the api/website or any other way.
     *
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @param phrase
     *         the phrase to find context for
     * @return the stream of contexts, containing the phrase and corresponding translations
     * @throws Exception
     *         if anything goes wrong while fetching
     */
    Stream<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception;
}
