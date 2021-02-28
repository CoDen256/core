package coden.reverso.data.context;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a sentence in any of the languages. The phrase within the sentence is
 * highlighted by {@link ReversoContextHighlight} and considered phrase in context.
 *
 * @author Denys Chernyshov
 */
public class ReversoContextSentence {

    /** The sentence (context) with the requested phrase in it. */
    private final String sentence;
    /** Tells where in the original sentence the requested phrase is mentioned */
    private final List<ReversoContextHighlight> highlights;

    /**
     * Creates a new context sentence
     *
     * @param sentence
     *         the context
     * @param highlights
     *         the list of highlights of the phrase in it.
     */
    public ReversoContextSentence(String sentence, List<ReversoContextHighlight> highlights) {
        this.sentence = sentence;
        this.highlights = highlights;
    }

    public String getSentence() {
        return sentence;
    }

    public List<ReversoContextHighlight> getHighlights() {
        return highlights;
    }

    /**
     * Returns the actual highlighted phrase within the sentence
     *
     * @param index
     *         the index of highlight
     * @return the substring within the sentence
     */
    public String getHighlight(int index) {
        return getHighlight(highlights.get(index));
    }

    /**
     * Returns the actual highlighted phrase within the sentence
     *
     * @param highlight
     *         the highlight
     * @return the phrase within the sentence
     */
    private String getHighlight(ReversoContextHighlight highlight) {
        return sentence.substring(highlight.getStart(), highlight.getEnd());
    }

    /**
     * The factory method to create a context sentence from the given original sentence and the list
     * of highlights within the sentence. Each 2 points in highlight points represents the start of highlight
     * and the end of the highlight, thus the list is divided by pairs
     *
     * @param sentence
     *         the original sentence
     * @param highlights
     *         the points where the phrase was mentioned
     * @return a new context sentence.
     */
    public static ReversoContextSentence fromPairHighlights(String sentence, List<Integer> highlights) {
        List<ReversoContextHighlight> highlightList = IntStream.range(0, highlights.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(n -> new ReversoContextHighlight(highlights.get(n), highlights.get(n + 1)))
                .collect(Collectors.toList());
        return new ReversoContextSentence(sentence, highlightList);
    }
}
