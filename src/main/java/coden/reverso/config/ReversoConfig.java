package coden.reverso.config;

import coden.reverso.client.crawler.ReversoDocumentFetcher;
import coden.reverso.website.ReversoUrls;
import org.jsoup.Jsoup;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ReversoConfig {

    @Bean("api-webclient")
    @ConditionalOnMissingBean
    WebClient apiWebClient() {
        return WebClient.create(ReversoUrls.TRANSLATE_ENDPOINT);
    }

    @Bean("query-service-webclient")
    @ConditionalOnMissingBean
    WebClient queryServiceWebClient() {
        return WebClient.create(ReversoUrls.QUERY_SERVICE_ENDPOINT);
    }

    @Bean
    @ConditionalOnMissingBean
    ReversoDocumentFetcher contextDocumentFetcher(){
        return (s, t, p) -> Jsoup.connect(ReversoUrls.getContextUrl(s, t, p)).get();
    }
}
