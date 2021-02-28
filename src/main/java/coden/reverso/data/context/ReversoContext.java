package coden.reverso.data.context;

/**
 * Represents a data class for reverso context that will be returned by the clients.
 * The context represents two sentences from different language, one is in source language,
 * the other is in target language. Each sentence contain the requested for translation phrase and
 * the highlights(or slices of string), where the phrase was mentioned
 *
 * @author Denys Chernyshov
 */
public class ReversoContext {

    /** The source sentence containing the requested phrase and the highlight for it */
    private final ReversoContextSentence sourceSentence;
    /** The target sentence(translation) containing the translated phrase in context and highlight for it */
    private final ReversoContextSentence targetSentence;

    /**
     * Creates a new context
     *
     * @param sourceSentence
     *         the sentence in source language
     * @param targetSentence
     *         the sentence in target language
     */
    public ReversoContext(ReversoContextSentence sourceSentence, ReversoContextSentence targetSentence) {
        this.sourceSentence = sourceSentence;
        this.targetSentence = targetSentence;
    }

    public ReversoContextSentence getSourceSentence() {
        return sourceSentence;
    }

    public ReversoContextSentence getTargetSentence() {
        return targetSentence;
    }
}
