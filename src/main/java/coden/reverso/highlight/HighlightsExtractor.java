package coden.reverso.highlight;

import java.util.regex.Pattern;

/**
 * Helper class that can extract highlight bzw. "em" tags. from the given html text.
 * As well as removing other links tags.
 *
 * @author Denys Chernyshov
 */
public class HighlightsExtractor {

    public static final String HIGHLIGHT_TAG_OPEN = "<em>";
    public static final String HIGHLIGHT_TAG_CLOSE = "</em>";
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

        removeLinks(innerText);
        while (isHighlighted(innerText.getText())) {
            cutNextHighlight(innerText);
        }

        return innerText;
    }


    /**
     * Helper method to cut next highlight, that is cut open and closing tag of highlight
     *
     * @param innerText
     *         the inner text
     */
    private static void cutNextHighlight(CuttableText innerText) {
        innerText.cutAndSave(HIGHLIGHT_TAG_OPEN);
        innerText.cutAndSave(HIGHLIGHT_TAG_CLOSE);
    }

    /**
     * Removes all the links from the text
     *
     * @param innerText
     *         the text
     */
    private static void removeLinks(CuttableText innerText) {
        innerText.cutAll(LINKS_PATTERN.pattern());
    }

    /**
     * Tells whether the sentence is highlighted
     *
     * @param sentence
     *         the sentence to check
     * @return {@code true} if sentence is highlighted {@code false} otherwise
     */
    private static boolean isHighlighted(String sentence) {
        return sentence.contains(HIGHLIGHT_TAG_OPEN) || sentence.contains(HIGHLIGHT_TAG_CLOSE);
    }

}
