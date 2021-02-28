package coden.reverso.config;

import coden.reverso.website.ReversoUrls;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Qualifier("api")
    @Bean
    WebClient apiWebClient() {
        return WebClient.create(ReversoUrls.TRANSLATE_ENDPOINT);
    }

    @Qualifier("query-service")
    @Bean
    WebClient queryServiceWebClient() {
        return WebClient.create(ReversoUrls.QUERY_SERVICE_ENDPOINT);
    }

}
