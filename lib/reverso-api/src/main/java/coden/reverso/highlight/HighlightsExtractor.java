package coden.reverso.highlight;

import java.util.regex.Pattern;

/**
 * Helper class that can extract highlight bzw. "em" tags. from the given html text.
 * As well as removing other links tags.
 *
 * @author Denys Chernyshov
 */
public class HighlightsExtractor {

    public static final String HIGHLIGHT_TAG_PATTERN = "</?em>";
    public static final Pattern LINKS_PATTERN = Pattern.compile("</?a[^>]*>");

    private HighlightsExtractor() {
    }

    /**
     * Cuts the given text by highlight tags and returns {@link CuttableText}
     * containing all the cut places.
     *
     * @param html
     *         the html to cut
     * @return the {@link CuttableText} with removed links, <em> tags and cut points where the tags were.
     */
    public static CuttableText extract(String html) {
        CuttableText innerText = new CuttableText(html);

        innerText.cutAll(LINKS_PATTERN.pattern());
        innerText.cutAllAndSave(HIGHLIGHT_TAG_PATTERN);

        return innerText;
    }
}
