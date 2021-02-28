package coden.reverso.config;

import coden.reverso.client.crawler.DocumentFetcher;
import coden.reverso.website.ReversoUrls;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Bean
    @Qualifier("api")
    WebClient apiWebClient() {
        return WebClient.create(ReversoUrls.TRANSLATE_ENDPOINT);
    }

    @Bean
    @Qualifier("query-service")
    WebClient queryServiceWebClient() {
        return WebClient.create(ReversoUrls.QUERY_SERVICE_ENDPOINT);
    }

    @Bean
    @Qualifier("context")
    DocumentFetcher contextDocumentFetcher(){
        return (s, t, p) -> Jsoup.connect(ReversoUrls.CONTEXT_URL_PATTERN).get();
    }
}
