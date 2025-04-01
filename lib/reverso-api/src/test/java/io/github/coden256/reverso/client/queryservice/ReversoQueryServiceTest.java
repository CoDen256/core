package io.github.coden256.reverso.client.queryservice;

import static org.junit.jupiter.api.Assertions.assertFalse;

import io.github.coden256.reverso.config.ReversoConfig;
import io.github.coden256.reverso.data.context.ReversoContext;
import io.github.coden256.reverso.data.context.ReversoContextClient;
import io.github.coden256.reverso.language.ReversoLanguage;
import org.junit.jupiter.api.Disabled;
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
@Disabled("POST Forbidden")
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