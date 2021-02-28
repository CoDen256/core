package coden.reverso.client.queryservice.context.response;

import coden.reverso.data.context.ReversoContext;
import coden.reverso.data.context.ReversoContextSentence;
import coden.reverso.highlight.CuttableText;
import coden.reverso.highlight.HighlightsExtractor;
import coden.reverso.language.ReversoLanguage;

import java.util.stream.Stream;

public class ReversoContextResponseMapper {

    public Stream<ReversoContext> map(ReversoContextResponse response){
        return response.getList().stream().map(this::mapToReversoContext);
    }

    private ReversoContext mapToReversoContext(ReversoContextEntryResponse response) {
        ReversoContextSentence sourceContext = createContextSentence(response.getSourceSentence());
        ReversoContextSentence targetContext = createContextSentence(response.getTargetSentence());
        return new ReversoContext(sourceContext, targetContext);
    }

    private ReversoContextSentence createContextSentence(String text){
        CuttableText extracted = HighlightsExtractor.extract(text);
        return ReversoContextSentence.fromPairHighlightPoints(extracted.getText(), extracted.getCutPoints());
    }
}
