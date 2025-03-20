package coden.reverso.language;

/**
 * Represents enum, that holds all the languages supported by reverso.
 * Each instance has different standards. For example when specified in url
 * the {@link ReversoLanguage#urlStandard} will be used to display language properly.
 *
 * @author Denys Chernyshov
 */
public enum ReversoLanguage {
    EN("english", "en", "eng"),
    DE("german", "de", "ger"),
    RU("russian", "ru", "rus");


    /** The standard name of language used in urls */
    private final String urlStandard;
    /** The standard name of language used by bst query service */
    private final String queryServiceStandard;
    /** The standard name of language used by api */
    private final String translationApiStandard;

    ReversoLanguage(String urlStandard, String queryServiceStandard, String translationApiStandard) {
        this.urlStandard = urlStandard;
        this.queryServiceStandard = queryServiceStandard;
        this.translationApiStandard = translationApiStandard;
    }

    public String getUrlStandard() {
        return urlStandard;
    }

    public String getQueryServiceStandard() {
        return queryServiceStandard;
    }

    public String getTranslationApiStandard() {
        return translationApiStandard;
    }
}
