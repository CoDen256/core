package io.github.coden256.reverso.client.crawler;

import io.github.coden256.reverso.data.context.ReversoContext;
import io.github.coden256.reverso.data.context.ReversoContextClient;
import io.github.coden256.reverso.data.context.ReversoContextSentence;
import io.github.coden256.reverso.highlight.CuttableText;
import io.github.coden256.reverso.highlight.HighlightsExtractor;
import io.github.coden256.reverso.language.ReversoLanguage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.stream.Stream;

/**
 * The crawler for reverso website. Parses only contexts.
 *
 * @author Denys Chernyshov
 */
public class ReversoCrawler implements ReversoContextClient {

    /** The Document fetcher, that will provide html document for reverso contexts */
    private final ReversoDocumentFetcher contextFetcher;

    public ReversoCrawler(@Qualifier("context") ReversoDocumentFetcher contextFetcher) {
        this.contextFetcher = contextFetcher;
    }

    @Override
    public Stream<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception {
        return parseContextDocument(contextFetcher.fetch(source, target, phrase));
    }

    /**
     * Parses the context document
     *
     * @param document
     *         the document to parse
     * @return the stream of {@link ReversoContext} that were parsed
     */
    private Stream<ReversoContext> parseContextDocument(Document document) {
        return document.getElementById("examples-content")
                .getElementsByClass("example")
                .stream()
                .map(this::extractContextExample);
    }


    /**
     * Extracts the {@link ReversoContext} from the context examples
     *
     * @param exampleElement
     *         the example element
     * @return the {@link ReversoContext}
     */
    private ReversoContext extractContextExample(Element exampleElement) {
        ReversoContextSentence srcSentence = extractContextSentence(exampleElement, "src");
        ReversoContextSentence trgSentence = extractContextSentence(exampleElement, "trg");

        return new ReversoContext(srcSentence, trgSentence);
    }

    /**
     * Extracts the context sentence from the given element
     *
     * @param element
     *         the element
     * @param className
     *         the class name of the inner element
     * @return the {@link ReversoContextSentence}
     */
    private ReversoContextSentence extractContextSentence(Element element, String className) {
        return extractHighlights(element.getElementsByClass(className)
                .first()
                .getElementsByClass("text")
                .first()
                .html());
    }

    /**
     * Extracts the highlight from the given html
     *
     * @param html
     *         the html to be cut
     * @return the {@link ReversoContextSentence}
     */
    private ReversoContextSentence extractHighlights(String html) {
        CuttableText extracted = HighlightsExtractor.extract(html);
        return ReversoContextSentence.fromPairHighlights(extracted.getText(), extracted.getCutPoints());
    }
}
