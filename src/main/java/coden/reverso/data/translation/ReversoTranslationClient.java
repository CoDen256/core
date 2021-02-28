package coden.reverso.data.translation;

import coden.reverso.data.context.ReversoContextHighlight;
import coden.reverso.language.ReversoLanguage;

import java.util.stream.Stream;

/**
 * Represents a reverso translation client that can fetch needed information and provide
 * stream of {@link ReversoContextHighlight} for the given request
 *
 * @author Denys Chernyshov
 * @see ReversoTranslation
 */
public interface ReversoTranslationClient {
    /**
     * Returns the stream of translations fetched from reverso.net, whether by crawler or by api.
     *
     * @param source
     *         the source language of the request
     * @param target
     *         the target language of the request
     * @param phrase
     *         the phrase to be translated
     * @return the stream of reverso translations
     * @throws Exception
     *         if something goes wrong while fetching the information for the translation
     */
    Stream<ReversoTranslation> getTranslations(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception;
}
