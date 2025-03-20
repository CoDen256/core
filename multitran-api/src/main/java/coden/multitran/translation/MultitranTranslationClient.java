package coden.multitran.translation;

import coden.multitran.language.MultitranLanguage;

import java.util.stream.Stream;

/**
 * Represents a general client to access multitran.com to obtain translations
 *
 * @author Denys Chernyshov
 */
public interface MultitranTranslationClient {
    /**
     * Obtains mutltitran translation from the mutltitran.com
     *
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @param phrase
     *         the phrase to request the translations for
     * @return the stream of multitran translations
     * @throws Exception
     *         if something during fetching the translations goes wrong
     */
    Stream<MultitranTranslation> getTranslations(MultitranLanguage source, MultitranLanguage target, String phrase) throws Exception;
}
