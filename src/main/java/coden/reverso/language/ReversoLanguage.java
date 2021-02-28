package coden.reverso.language;

public enum ReversoLanguage {
    EN("english", "en", "eng"),
    DE("german", "de", "ger"),
    RU("russian", "ru", "rus");


    private final String urlStandard;
    private final String contextApiStandard;
    private final String translationApiStandard;

    ReversoLanguage(String urlStandard, String contextApiStandard, String translationApiStandard) {
        this.urlStandard = urlStandard;
        this.contextApiStandard = contextApiStandard;
        this.translationApiStandard = translationApiStandard;
    }

    public String getUrlStandard() {
        return urlStandard;
    }

    public String getContextApiStandard() {
        return contextApiStandard;
    }

    public String getTranslationApiStandard() {
        return translationApiStandard;
    }
}
