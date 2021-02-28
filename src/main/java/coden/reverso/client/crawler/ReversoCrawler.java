package coden.reverso.client.crawler;

import static coden.reverso.website.ReversoUrls.getContextUrl;

import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.context.ReversoContextClient;
import coden.reverso.data.context.ReversoContextSentence;
import coden.reverso.highlight.CuttableText;
import coden.reverso.highlight.HighlightsExtractor;
import coden.reverso.language.ReversoLanguage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ReversoCrawler implements ReversoContextClient {

    @Override
    public Stream<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        String url = getContextUrl(source, target, phrase);
        Document document = Jsoup.connect(url).get();
        return parseDocumentContext(document);
    }

    private Stream<ReversoContext> parseDocumentContext(Document document) {
        return document.getElementById("examples-content")
                .getElementsByClass("example")
                .stream()
                .map(this::extractContext);
    }


    private ReversoContext extractContext(Element exampleElement) {
        ReversoContextSentence srcSentence = extractContextSentence(exampleElement, "src");
        ReversoContextSentence trgSentence = extractContextSentence(exampleElement, "trg");

        return new ReversoContext(srcSentence, trgSentence);
    }

    private ReversoContextSentence extractContextSentence(Element element, String className) {
        return extractHighlights(element.getElementsByClass(className)
                .first()
                .getElementsByClass("text")
                .first()
                .html());
    }

    private ReversoContextSentence extractHighlights(String html) {
        CuttableText extracted = HighlightsExtractor.extract(html);
        return ReversoContextSentence.fromPairHighlightPoints(extracted.getText(), extracted.getCutPoints());
    }
}
