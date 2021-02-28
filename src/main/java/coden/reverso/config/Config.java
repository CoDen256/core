package coden.reverso.config;

import coden.reverso.website.ReversoUrls;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Qualifier("translation-webclient")
    WebClient translationApiWebClient(){
        return WebClient.create(ReversoUrls.TRANSLATE_API);
    }

    @Qualifier("context-webclient")
    WebClient contextApiWebClient(){
        return WebClient.create(ReversoUrls.CONTEXT_API);
    }

}
