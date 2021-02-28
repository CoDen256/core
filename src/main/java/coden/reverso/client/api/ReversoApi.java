package coden.reverso.client.api;

import coden.reverso.client.api.context.ReversoContextRequest;
import coden.reverso.client.api.context.ReversoContextResponse;
import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.context.ReversoContextClient;
import coden.reverso.data.context.ReversoContextSentence;
import coden.reverso.data.translation.ReversoTranslation;
import coden.reverso.data.translation.ReversoTranslationClient;
import coden.reverso.highlight.CuttableText;
import coden.reverso.highlight.HighlightsExtractor;
import coden.reverso.language.ReversoLanguage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class ReversoApi implements ReversoContextClient, ReversoTranslationClient {

    private final WebClient contextClient;
    private final WebClient translationClient;

    public ReversoApi(@Qualifier("context-webclient") WebClient contextClient,
                      @Qualifier("translation-webclient") WebClient translationClient) {
        this.contextClient = contextClient;
        this.translationClient = translationClient;
    }

    // TODO: may be not block, and web client via config
    @Override
    public List<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        return contextClient.post()
                .bodyValue(createRequest(source, target, phrase))
                .retrieve()
                .bodyToMono(ReversoContextResponse.class)
                .block()
                .getList()
                .stream()
                .map(this::mapToContextTranslations)
                .collect(Collectors.toList());
    }

    private ReversoContextRequest createRequest(ReversoLanguage source, ReversoLanguage target, String phrase){
        ReversoContextRequest request = new ReversoContextRequest();
        request.setMode(0);
        request.setNPage(1);
        request.setSourceLang(source.getContextApiStandard());
        request.setTargetLang(target.getContextApiStandard());
        request.setSourceText(phrase);
        request.setTargetText("");
        return request;
    }

    private ReversoContext mapToContextTranslations(ReversoContextResponse.Context response){
        ReversoContextSentence sourceContext = createContextSentence(response.getSourceSentence());
        ReversoContextSentence targetContext = createContextSentence(response.getTargetSentence());
        return new ReversoContext(sourceContext, targetContext);
    }

    private ReversoContextSentence createContextSentence(String text){
        CuttableText extracted = HighlightsExtractor.extract(text);
        return ReversoContextSentence.fromPairHighlightPoints(extracted.getText(), extracted.getCutPoints());
    }


    @Override
    public List<ReversoTranslation> getTranslations(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        return null;
    }


    private ReversoTranslationRequest createRequest(Language source, Language target, String phrase){
        ReversoTranslationRequest request = new ReversoTranslationRequest();
        request.setMode(0);
        request.setNPage(1);
        request.setSourceLang(ctxResolver.resolve(source));
        request.setTargetLang(ctxResolver.resolve(target));
        request.setSourceText(phrase);
        request.setTargetText("");
        return request;
    }

    private ReversoContextTranslation mapToTranslations(ReversoTranslationResponse.Context response){
        ReversoContextSentence sourceContext = createContextSentence(response.getSourceSentence());
        ReversoContextSentence targetContext = createContextSentence(response.getTargetSentence());
        return new ReversoContextTranslation(sourceContext, targetContext);
    }
}
