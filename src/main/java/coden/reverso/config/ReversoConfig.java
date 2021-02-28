package coden.reverso.config;

import coden.reverso.client.crawler.ReversoDocumentFetcher;
import coden.reverso.website.ReversoUrls;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ReversoConfig {

    @Bean
    @ConditionalOnMissingBean
    @Qualifier("api")
    WebClient apiWebClient() {
        return WebClient.create(ReversoUrls.TRANSLATE_ENDPOINT);
    }

    @Bean
    @ConditionalOnMissingBean
    @Qualifier("query-service")
    WebClient queryServiceWebClient() {
        return WebClient.create(ReversoUrls.QUERY_SERVICE_ENDPOINT);
    }

    @Bean
    @ConditionalOnMissingBean
    ReversoDocumentFetcher contextDocumentFetcher(){
        return (s, t, p) -> Jsoup.connect(ReversoUrls.CONTEXT_URL_PATTERN).get();
    }
}
