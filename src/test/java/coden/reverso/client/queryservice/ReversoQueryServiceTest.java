package coden.reverso.client.queryservice;

import static org.junit.jupiter.api.Assertions.assertFalse;

import coden.reverso.config.ReversoConfig;
import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.context.ReversoContextClient;
import coden.reverso.language.ReversoLanguage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ReversoConfig.class)
class ReversoQueryServiceTest {

    @Qualifier("reverso-query")
    @Autowired
    private ReversoContextClient client;


    @Test
    void getContexts() throws Exception {
        List<ReversoContext> contexts = client
                .getContexts(ReversoLanguage.DE, ReversoLanguage.EN, "durchziehen")
                .collect(Collectors.toList());
        assertFalse(contexts.isEmpty());
    }
}