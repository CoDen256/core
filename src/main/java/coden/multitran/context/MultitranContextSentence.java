package coden.multitran.context;

/**
 * The context sentence containing the requested phrase.
 *
 * @author Denys Chernyshov
 */
public class MultitranContextSentence {
    private String sentence;

    public MultitranContextSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
