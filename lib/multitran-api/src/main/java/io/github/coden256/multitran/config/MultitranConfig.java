package io.github.coden256.multitran.config;

import io.github.coden256.multitran.crawler.MultitranCrawler;
import io.github.coden256.multitran.crawler.MultitranDocumentFetcher;
import io.github.coden256.multitran.translation.MultitranTranslationClient;
import io.github.coden256.multitran.website.MultitranUrls;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultitranConfig {

    @Bean
    MultitranDocumentFetcher documentFetcher(){
        return (s, t, p) -> Jsoup.connect(MultitranUrls.getTranslationUrl(s, t, p)).get();
    }

    @Bean
    MultitranTranslationClient multitranClient(MultitranDocumentFetcher fetcher){
        return new MultitranCrawler(fetcher);
    }

}
