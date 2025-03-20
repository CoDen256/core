package coden.reverso.data.context;

/**
 * Represents a highlight within the sentence. Actually just a range.
 *
 * @author Denys Chernyshov
 */
public class ReversoContextHighlight {
    /** The start of mentioning */
    private final int start;
    /** The end of mentioning */
    private final int end;

    /**
     * Creates a new highlight
     *
     * @param start
     *         the start index
     * @param end
     *         the end index
     */
    public ReversoContextHighlight(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
