package coden.reverso.data.context;

public class ReversoContext {

    private final ReversoContextSentence sourceSentence;
    private final ReversoContextSentence targetSentence;

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
