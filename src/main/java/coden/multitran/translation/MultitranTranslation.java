package coden.multitran.translation;

/**
 * Represents a translation parsed from multitran.com
 *
 * @author Denys Chernyshov
 */
public class MultitranTranslation {

    private String translation;

    public MultitranTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
