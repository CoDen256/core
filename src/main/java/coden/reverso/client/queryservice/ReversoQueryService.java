package coden.reverso.client.queryservice;

import coden.reverso.client.queryservice.context.request.ReversoContextRequestMapper;
import coden.reverso.client.queryservice.context.response.ReversoContextResponse;
import coden.reverso.client.queryservice.context.response.ReversoContextResponseMapper;
import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.context.ReversoContextClient;
import coden.reverso.language.ReversoLanguage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Stream;

public class ReversoQueryService implements ReversoContextClient {

    private final WebClient webClient;
    private final ReversoContextRequestMapper reversoContextRequestMapper;
    private final ReversoContextResponseMapper reversoContextResponseMapper;

    public ReversoQueryService(@Qualifier("query-service") WebClient webClient) {
        this.webClient = webClient;
        reversoContextRequestMapper = new ReversoContextRequestMapper();
        reversoContextResponseMapper = new ReversoContextResponseMapper();
    }

    @Override
    public Stream<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        ReversoContextResponse response = webClient.post()
                .bodyValue(reversoContextRequestMapper.map(source, target, phrase))
                .retrieve()
                .bodyToMono(ReversoContextResponse.class)
                .block();
        return reversoContextResponseMapper.map(response);
    }
}
