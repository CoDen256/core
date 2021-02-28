package coden.reverso.client.api.translation.request;

import coden.reverso.client.api.translation.request.ReversoTranslationOptions;
import coden.reverso.client.api.translation.request.ReversoTranslationRequest;
import coden.reverso.language.ReversoLanguage;

public class ReversoTranslationRequestMapper {
    public ReversoTranslationRequestMapper() {
    }

    public ReversoTranslationRequest map(ReversoLanguage source, ReversoLanguage target, String phrase) {
        ReversoTranslationRequest request = new ReversoTranslationRequest();
        ReversoTranslationOptions options = new ReversoTranslationOptions();
        options.setOrigin("reversodesktop");
        options.setSentenceSplitter(true);
        options.setContextResults(true);
        options.setContextResults(true);
        request.setFormat("text");
        request.setInput(phrase);
        request.setSourceLanguage(source.getTranslationApiStandard());
        request.setTargetLanguage(target.getTranslationApiStandard());
        request.setOptions(options);
        return request;
    }
}