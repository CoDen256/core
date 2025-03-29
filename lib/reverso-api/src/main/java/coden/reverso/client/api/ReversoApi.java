package coden.reverso.client.api;

import coden.reverso.ReversoClient;
import coden.reverso.client.api.translation.request.ApiRequest;
import coden.reverso.client.api.translation.request.ApiRequestOptions;
import coden.reverso.client.api.translation.response.ApiContextEntryResponse;
import coden.reverso.client.api.translation.response.ApiResponse;
import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.context.ReversoContextSentence;
import coden.reverso.data.translation.ReversoTranslation;
import coden.reverso.highlight.CuttableText;
import coden.reverso.highlight.HighlightsExtractor;
import coden.reverso.language.ReversoLanguage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Represents the actual reverso.net API used to obtain information from the reverso.net.
 * Including the translation and contexts
 *
 * @author Denys Chernyshov
 */
public class ReversoApi implements ReversoClient {

    /** The Web Client used to make requests to reverso translation api */
    private final WebClient webClient;

    public ReversoApi(@Qualifier("api") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Stream<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        ApiRequest request = buildRequest(buildApiRequestOptions())
                .setInput(phrase)
                .setSourceLanguage(source.getTranslationApiStandard())
                .setTargetLanguage(target.getTranslationApiStandard())
                .create();

        ApiResponse response = doApiRequest(request);
        return mapToContexts(response);
    }

    /**
     * Maps the given {@link ApiResponse} to {@link ReversoContext} extracting
     * the translations returned by response
     *
     * @param response
     *         the given response fetched from reverso api
     * @return the stream of contexts
     */
    private Stream<ReversoContext> mapToContexts(ApiResponse response) {
        return response.getContextResults().getEntries().stream().flatMap(this::extractContexts);
    }

    /**
     * Maps the given context entry response from reverso.net api to the stream of {@link ReversoContext}s
     *
     * @param entry
     *         the context entry response
     * @return the reverso context
     */
    private Stream<ReversoContext> extractContexts(ApiContextEntryResponse entry) {
        List<String> source = entry.getSourceExamples();
        List<String> target = entry.getTargetExamples();
        return IntStream.range(0, source.size())
                .mapToObj(i -> createContext(source.get(i), target.get(i)));
    }

    /**
     * Creates a new {@link ReversoContext} from the given source sentence and target sentence
     *
     * @param source
     *         the source sentence
     * @param target
     *         the target sentence
     * @return a new reverso context from the given sentences
     */
    private ReversoContext createContext(String source, String target) {
        ReversoContextSentence sourceContext = createContextSentence(source);
        ReversoContextSentence targetContext = createContextSentence(target);
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


    @Override
    public Stream<ReversoTranslation> getTranslations(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        ApiRequest request = buildRequest(buildApiRequestOptions())
                .setInput(phrase)
                .setSourceLanguage(source.getTranslationApiStandard())
                .setTargetLanguage(target.getTranslationApiStandard())
                .create();

        ApiResponse response = doApiRequest(request);
        return mapToTranslations(response);
    }

    /**
     * Maps the given {@link ApiResponse} to {@link ReversoTranslation} extracting
     * the translations returned by response
     *
     * @param response
     *         the given response fetched from reverso api
     * @return the stream of translations
     */
    private Stream<ReversoTranslation> mapToTranslations(ApiResponse response) {
        return response.getTranslation().stream().map(ReversoTranslation::new);
    }

    /**
     * Returns a request Builder for {@link ApiRequest} with default set values
     * and given options
     *
     * @param options
     *         the options of the api request
     * @return the request builder
     */
    // TODO: specify via config
    private ApiRequest.Builder buildRequest(ApiRequestOptions options) {
        return new ApiRequest.Builder()
                .setFormat("text")
                .setOptions(options);

    }

    /**
     * Builds a default request options for making an api request.
     *
     * @return the options for api request.
     */
    private ApiRequestOptions buildApiRequestOptions() {
        return new ApiRequestOptions.Builder()
                .enableContextResults()
                .enableSentenceSplitter()
                .enableSentenceSplitter()
                .setOrigin("reversodesktop")
                .create();
    }

    /**
     * Makes an actual call to reverso api to get api response
     *
     * @param request
     *         the request to be passed in the body
     * @return the {@link ApiResponse} containing contexts and translations
     */
    private ApiResponse doApiRequest(ApiRequest request) {
        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .block();
    }
}
