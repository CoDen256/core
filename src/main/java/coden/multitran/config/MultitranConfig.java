package coden.multitran.config;

import coden.multitran.crawler.MultitranDocumentFetcher;
import coden.multitran.website.MultitranUrls;
import org.jsoup.Jsoup;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultitranConfig {

    @Bean
    @ConditionalOnMissingBean
    MultitranDocumentFetcher documentFetcher(){
        return (s, t, p) -> Jsoup.connect(MultitranUrls.getTranslationUrl(s, t, p)).get();
    }

}
