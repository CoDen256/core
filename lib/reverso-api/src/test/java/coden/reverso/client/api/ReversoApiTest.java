package coden.reverso.client.api;


import static org.junit.jupiter.api.Assertions.assertFalse;

import coden.reverso.ReversoClient;
import coden.reverso.config.ReversoConfig;
import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.translation.ReversoTranslation;
import coden.reverso.language.ReversoLanguage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ReversoConfig.class)
class ReversoApiTest {

    @Qualifier("reverso-api")
    @Autowired
    private ReversoClient reversoClient;


    @Test
    void getContexts() throws Exception {
        List<ReversoContext> contexts = reversoClient
                .getContexts(ReversoLanguage.EN, ReversoLanguage.DE, "negotiate")
                .collect(Collectors.toList());

        assertFalse(contexts.isEmpty());
    }

    @Test
    void getTranslations() throws Exception {
        List<ReversoTranslation> translations = reversoClient
                .getTranslations(ReversoLanguage.EN, ReversoLanguage.DE, "negotiate")
                .collect(Collectors.toList());

        assertFalse(translations.isEmpty());
    }
}