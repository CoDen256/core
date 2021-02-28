package coden.reverso.highlight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;

class CuttableTextTest {

    public static final String ORIGINAL_STRING = "Lorem ipsum, random text, really random text";

    @Test
    void getOriginalString() {
        CuttableText cuttableText = new CuttableText(ORIGINAL_STRING);
        cuttableText.cut("lorem");
        cuttableText.cut("random text");
        assertNotEquals(ORIGINAL_STRING, cuttableText.getText());
        assertEquals(ORIGINAL_STRING, cuttableText.getOriginalString());
    }

    @Test
    void cutAndSave() {
        CuttableText cuttableText = new CuttableText(ORIGINAL_STRING);
        cuttableText.cutAndSave("[r]");
        assertEquals("Loem ipsum, random text, really random text", cuttableText.getText());
        List<Integer> cuts = List.of(2);
        assertEquals(cuts.size(), cuttableText.getCutPoints().size());
        assertTrue(cuts.containsAll(cuttableText.getCutPoints()));
    }

    @Test
    void cut() {
        CuttableText cuttableText = new CuttableText(ORIGINAL_STRING);
        cuttableText.cut("[r]");
        assertEquals("Loem ipsum, random text, really random text", cuttableText.getText());
        assertTrue(cuttableText.getCutPoints().isEmpty());
    }

    @Test
    void cutAllAndSave() {
        CuttableText cuttableText = new CuttableText(ORIGINAL_STRING);
        cuttableText.cutAllAndSave("[r]");
        assertEquals("Loem ipsum, andom text, eally andom text", cuttableText.getText());
        List<Integer> cuts = List.of(2, 12, 24, 30);
        assertEquals(cuts.size(), cuttableText.getCutPoints().size());
        assertTrue(cuts.containsAll(cuttableText.getCutPoints()));
    }

    @Test
    void cutAll() {
        CuttableText cuttableText = new CuttableText(ORIGINAL_STRING);
        cuttableText.cutAll("[r]");
        assertEquals("Loem ipsum, andom text, eally andom text", cuttableText.getText());
        assertTrue(cuttableText.getCutPoints().isEmpty());
    }


    @Test
    void resetCutPoints() {
        CuttableText cuttableText = new CuttableText(ORIGINAL_STRING);
        cuttableText.cutAllAndSave("[r]");
        assertEquals("Loem ipsum, andom text, eally andom text", cuttableText.getText());
        assertFalse(cuttableText.getCutPoints().isEmpty());
        cuttableText.resetCutPoints();
        assertTrue(cuttableText.getCutPoints().isEmpty());
    }

    @Test
    void reset() {
        CuttableText cuttableText = new CuttableText(ORIGINAL_STRING);
        cuttableText.cutAllAndSave("[r]");
        assertEquals("Loem ipsum, andom text, eally andom text", cuttableText.getText());
        assertFalse(cuttableText.getCutPoints().isEmpty());
        cuttableText.reset();
        assertTrue(cuttableText.getCutPoints().isEmpty());
        assertEquals(ORIGINAL_STRING, cuttableText.getText());
    }
}