package coden.reverso.website;

import coden.reverso.language.ReversoLanguage;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class ReversoWebsite {

    public static final String CONTEXT_URL = "https://www.context.reverso.net/translation/{source}-{target}/{phrase}";
    public static final String TRANSLATION_URL = "https://www.reverso.net/translationresults.aspx?lang=EN&direction={source}-{target}";
    private ReversoWebsite(){
    }

    public static String getContextUrl(ReversoLanguage source, ReversoLanguage target, String phrase) {
        return StringSubstitutor.replace(CONTEXT_URL, Map.of(
                "phrase", phrase,
                "source", source.getUrlStandard(),
                "target", target.getUrlStandard()), "{", "}");
    }

    public static String getTranslationUrl(ReversoLanguage source, ReversoLanguage target, String phrase) {
        return StringSubstitutor.replace(TRANSLATION_URL, Map.of(
                "source", source.getUrlStandard(),
                "target", target.getUrlStandard()), "{", "}");
    }
}
