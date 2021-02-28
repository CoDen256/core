package coden.reverso.highlight;

import java.util.regex.Pattern;

public class HighlightsExtractor {

    public static final String HIGHLIGHT_TAG_OPEN = "<em>";
    public static final String HIGHLIGHT_TAG_CLOSE = "</em>";
    public static final Pattern LINKS_PATTERN = Pattern.compile("</?a[^>]*>");

    private HighlightsExtractor(){
    }

    public static CuttableText extract(String text){
        CuttableText innerText = new CuttableText(text);

        removeLinks(innerText);
        while (isHighlighted(innerText.getText())){
            cutNextHighlight(innerText);
        }
        
        return innerText;
    }
    
    private static void cutNextHighlight(CuttableText innerText) {
        innerText.cut(HIGHLIGHT_TAG_OPEN);
        innerText.cut(HIGHLIGHT_TAG_CLOSE);
    }

    private static void removeLinks(CuttableText innerText) {
        innerText.cutAll(LINKS_PATTERN.pattern());
    }

    private static boolean isHighlighted(String sentence){
        return sentence.contains(HIGHLIGHT_TAG_OPEN) || sentence.contains(HIGHLIGHT_TAG_CLOSE);
    }

}
