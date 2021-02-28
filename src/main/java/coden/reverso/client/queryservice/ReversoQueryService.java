package coden.reverso.client.queryservice;

import coden.reverso.client.queryservice.request.QueryServiceRequest;
import coden.reverso.client.queryservice.response.QueryServiceEntryResponse;
import coden.reverso.client.queryservice.response.QueryServiceResponse;
import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.context.ReversoContextClient;
import coden.reverso.data.context.ReversoContextSentence;
import coden.reverso.highlight.CuttableText;
import coden.reverso.highlight.HighlightsExtractor;
import coden.reverso.language.ReversoLanguage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Stream;

/**
 * Represents something like reverso api, but only for contexts only.
 *
 * @author Denys Chernyshov
 */
public class ReversoQueryService implements ReversoContextClient {

    /** The Web client to make api requests */
    private final WebClient webClient;

    /**
     * Creates a new {@link ReversoQueryService} from the given web client
     *
     * @param webClient
     *         the web client pointing to api endpoint.
     */
    public ReversoQueryService(@Qualifier("query-service") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Stream<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        QueryServiceRequest request = buildRequest(source, target, phrase);
        QueryServiceResponse response = doApiRequest(request);
        return mapToContexts(response);
    }

    /**
     * Creates a new request from the given request params. Sets some fields on their default values
     *
     * @param source
     *         the source language
     * @param target
     *         the target language
     * @param phrase
     *         the phrase to be translated
     * @return the request
     */
    private QueryServiceRequest buildRequest(ReversoLanguage source, ReversoLanguage target, String phrase) {
        return new QueryServiceRequest.Builder()
                .setMode(0)
                .setPageCount(1)
                .setSourceLang(source.getQueryServiceStandard())
                .setTargetLang(target.getQueryServiceStandard())
                .setSourceText(phrase)
                .setTargetText("") // because we want actually to obtain it
                .create();
    }


    /**
     * Calls the actual api request to query service serializing given {@link QueryServiceRequest}
     *
     * @param request
     *         the request to post
     * @return the deserialized from json {@link QueryServiceResponse}
     */
    // TODO: block? nullable?
    private QueryServiceResponse doApiRequest(QueryServiceRequest request) {
        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(QueryServiceResponse.class)
                .block();
    }

    /**
     * Maps given response to stream of reverso contexts. The {@link QueryServiceResponse} contains
     * multiple {@link QueryServiceEntryResponse}s, that are consequently mapped to {@link ReversoContext}
     *
     * @param response
     *         the response to map
     * @return the contexts contained by the response
     */
    public Stream<ReversoContext> mapToContexts(QueryServiceResponse response) {
        return response.getList().stream().map(this::mapToContext);
    }

    /**
     * Maps the given response entry to reverso context
     *
     * @param response
     *         the response to map
     * @return the reverso context
     */
    private ReversoContext mapToContext(QueryServiceEntryResponse response) {
        ReversoContextSentence sourceContext = createContextSentence(response.getSourceSentence());
        ReversoContextSentence targetContext = createContextSentence(response.getTargetSentence());
        return new ReversoContext(sourceContext, targetContext);
    }

    /**
     * Creates a context sentence from the given text(will be fetched in html)
     *
     * @param text
     *         the text containing highlighted phrase
     * @return the {@link ReversoContextSentence}
     */
    private ReversoContextSentence createContextSentence(String text) {
        CuttableText extracted = HighlightsExtractor.extract(text);
        return ReversoContextSentence.fromPairHighlights(extracted.getText(), extracted.getCutPoints());
    }
}
