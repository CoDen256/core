package coden.reverso.crawler;

import coden.reverso.context.ReversoContext;
import coden.reverso.context.ReversoContextClient;
import coden.reverso.context.ReversoContextSentence;
import coden.reverso.highlight.CuttableText;
import coden.reverso.language.ReversoLanguage;
import coden.reverso.website.ReversoWebsite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReversoCrawler implements ReversoContextClient {

    @Override
    public List<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        String url = getContextUrl(source, target, phrase);
        Document document = Jsoup.connect(url).get();
        return parseDocumentContext(document);
    }

    private List<ReversoContext> parseDocumentContext(Document document){
        return document.getElementById("examples-content")
                .getElementsByClass("example")
                .stream()
                .map(this::extractContext)
                .collect(Collectors.toList());
    }


    private ReversoContext extractContext(Element exampleElement){
        ReversoContextSentence srcSentence = extractContextSentence(exampleElement, "src");
        ReversoContextSentence trgSentence = extractContextSentence(exampleElement, "trg");

        return new ReversoContext(srcSentence, trgSentence);
    }

    private ReversoContextSentence extractContextSentence(Element element, String className){
        return extractHighlights(element.getElementsByClass(className)
                .first()
                .getElementsByClass("text")
                .first()
                .html());
    }

    private ReversoContextSentence extractHighlights(String html) {
        CuttableText extracted = extractor.extract(html);
        return ReversoContextSentence.fromPairHighlightPoints(extracted.getText(), extracted.getCutPoints());
    }

    @Override
    public String getContextUrl(ReversoLanguage source, ReversoLanguage target, String phrase) {
        return ReversoWebsite.getContextUrl(source, target, phrase);
    }
}
