package coden.multitran.crawler;

import static org.junit.jupiter.api.Assertions.assertFalse;

import coden.multitran.config.MultitranConfig;
import coden.multitran.language.MultitranLanguage;
import coden.multitran.translation.MultitranTranslation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MultitranConfig.class)
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