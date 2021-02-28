package coden.reverso.highlight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;

class HighlightsExtractorTest {

    @Test
    void extract() {
        String html = "Hello this is usual html <em>text</em> and some of the places"
                + " are highlighted actually 2 times like <a href=bla><em>this</em></a>";
        CuttableText result = HighlightsExtractor.extract(html);

        assertEquals("Hello this is usual html text and some of the places are highlighted"
                + " actually 2 times like this", result.getText());
        List<Integer> cuts = List.of(25, 29, 91, 95);
        assertEquals(cuts.size(), result.getCutPoints().size());
        assertTrue(cuts.containsAll(result.getCutPoints()));

    }
}