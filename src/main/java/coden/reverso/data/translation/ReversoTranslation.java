package coden.reverso.data.translation;

/**
 * Represents the translation that is provided by clients.
 * Just a wrapper class for the actual translation
 *
 * @author Denys Chernyshov
 */
public class ReversoTranslation {

    /** The translation */
    private final String translation;

    /**
     * Creates a new translation from the given string
     *
     * @param translation
     *         the given translation
     */
    public ReversoTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
