package coden.reverso.client.api;

import coden.reverso.client.api.translation.request.ReversoTranslationRequestMapper;
import coden.reverso.client.api.translation.response.ReversoTranslationResponse;
import coden.reverso.client.api.translation.response.ReversoTranslationResponseMapper;
import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.context.ReversoContextClient;
import coden.reverso.data.translation.ReversoTranslation;
import coden.reverso.data.translation.ReversoTranslationClient;
import coden.reverso.language.ReversoLanguage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReversoApi implements ReversoContextClient, ReversoTranslationClient {

    private final WebClient webClient;
    private final ReversoTranslationRequestMapper reversoTranslationRequestMapper = new ReversoTranslationRequestMapper();
    private final ReversoTranslationResponseMapper reversoTranslationResponseMapper = new ReversoTranslationResponseMapper();

    public ReversoApi(@Qualifier("api") WebClient webClient) {
        this.webClient = webClient;
    }

    // TODO: may be not block, and web client via config
    @Override
    public List<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        return null;
    }


    @Override
    public List<ReversoTranslation> getTranslations(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        ReversoTranslationResponse response = webClient.post()
                .bodyValue(reversoTranslationRequestMapper.map(source, target, phrase))
                .retrieve()
                .bodyToMono(ReversoTranslationResponse.class)
                .block();
        return reversoTranslationResponseMapper.map(response).collect(Collectors.toList());
    }
}
