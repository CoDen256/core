package coden.reverso.client.api.translation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReversoTranslationOptions {
    @JsonProperty("origin")
    private String origin;

    @JsonProperty("sentenceSplitter")
    private boolean sentenceSplitter;

    @JsonProperty("contextResults")
    private boolean contextResults;

    @JsonProperty("languageDetection")
    private boolean languageDetection;


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isSentenceSplitter() {
        return sentenceSplitter;
    }

    public void setSentenceSplitter(boolean sentenceSplitter) {
        this.sentenceSplitter = sentenceSplitter;
    }

    public boolean isContextResults() {
        return contextResults;
    }

    public void setContextResults(boolean contextResults) {
        this.contextResults = contextResults;
    }

    public boolean isLanguageDetection() {
        return languageDetection;
    }

    public void setLanguageDetection(boolean languageDetection) {
        this.languageDetection = languageDetection;
    }
}
