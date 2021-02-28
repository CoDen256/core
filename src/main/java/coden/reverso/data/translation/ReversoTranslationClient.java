package coden.reverso.data.translation;

import coden.reverso.language.ReversoLanguage;

import java.util.List;

public interface ReversoTranslationClient {
    List<ReversoTranslation> getTranslations(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception;
}
