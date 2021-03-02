package coden.multitran.config;

import coden.multitran.crawler.MultitranCrawler;
import coden.multitran.crawler.MultitranDocumentFetcher;
import coden.multitran.translation.MultitranTranslationClient;
import coden.multitran.website.MultitranUrls;
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
