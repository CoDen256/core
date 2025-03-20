package coden.reverso.config;

import coden.reverso.ReversoClient;
import coden.reverso.client.api.ReversoApi;
import coden.reverso.client.crawler.ReversoCrawler;
import coden.reverso.client.crawler.ReversoDocumentFetcher;
import coden.reverso.client.queryservice.ReversoQueryService;
import coden.reverso.data.context.ReversoContextClient;
import coden.reverso.website.ReversoUrls;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ReversoConfig {

    @Bean("api-webclient")
    WebClient apiWebClient() {
        return WebClient.create(ReversoUrls.TRANSLATE_ENDPOINT);
    }

    @Bean("reverso-api")
    ReversoClient reversoApi(@Qualifier("api-webclient") WebClient webClient){
        return new ReversoApi(webClient);
    }

    @Bean("query-webclient")
    WebClient queryServiceWebClient() {
        return WebClient.create(ReversoUrls.QUERY_SERVICE_ENDPOINT);
    }

    @Bean("reverso-query")
    ReversoContextClient reversoQuery(@Qualifier("query-webclient") WebClient webClient){
        return new ReversoQueryService(webClient);
    }

    @Bean
    ReversoDocumentFetcher contextDocumentFetcher(){
        return (s, t, p) -> Jsoup.connect(ReversoUrls.getContextUrl(s, t, p)).get();
    }

    @Bean("reverso-crawler")
    ReversoContextClient reversoCrawler(ReversoDocumentFetcher fetcher){
        return new ReversoCrawler(fetcher);
    }

}
