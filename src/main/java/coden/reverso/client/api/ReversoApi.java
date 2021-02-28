package coden.reverso.client.api;

import coden.reverso.ReversoClient;
import coden.reverso.client.api.translation.request.ReversoTranslationRequestMapper;
import coden.reverso.client.api.translation.response.ReversoTranslationResponse;
import coden.reverso.client.api.translation.response.ReversoTranslationResponseMapper;
import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.translation.ReversoTranslation;
import coden.reverso.language.ReversoLanguage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Stream;

@Component
public class ReversoApi implements ReversoClient {

    private final WebClient webClient;
    private final ReversoTranslationRequestMapper reversoTranslationRequestMapper = new ReversoTranslationRequestMapper();
    private final ReversoTranslationResponseMapper reversoTranslationResponseMapper = new ReversoTranslationResponseMapper();

    public ReversoApi(@Qualifier("api") WebClient webClient) {
        this.webClient = webClient;
    }

    // TODO: may be not block, and web client via config
    @Override
    public Stream<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        return null;
    }


    @Override
    public Stream<ReversoTranslation> getTranslations(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        ReversoTranslationResponse response = webClient.post()
                .bodyValue(reversoTranslationRequestMapper.map(source, target, phrase))
                .retrieve()
                .bodyToMono(ReversoTranslationResponse.class)
                .block();
        return reversoTranslationResponseMapper.map(response);
    }
}
