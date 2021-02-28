package coden.reverso.data.translation;

import coden.reverso.language.ReversoLanguage;

import java.util.stream.Stream;

public interface ReversoTranslationClient {
    Stream<ReversoTranslation> getTranslations(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception;
}
