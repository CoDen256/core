package io.github.coden256.multitran.crawler;

import static org.junit.jupiter.api.Assertions.assertFalse;

import io.github.coden256.multitran.config.MultitranConfig;
import io.github.coden256.multitran.language.MultitranLanguage;
import io.github.coden256.multitran.translation.MultitranTranslation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MultitranConfig.class)
@Disabled("Integration test may be unstable")
class MultitranCrawlerTest {

    @Autowired
    MultitranDocumentFetcher multitranDocumentFetcher;

    @Test
    void getTranslations() throws Exception {

        MultitranCrawler crawler = new MultitranCrawler(multitranDocumentFetcher);

        List<MultitranTranslation> translations = crawler
                .getTranslations(MultitranLanguage.DE, MultitranLanguage.EN, "entwerfen")
                .collect(Collectors.toList());

        assertFalse(translations.isEmpty());
    }
}