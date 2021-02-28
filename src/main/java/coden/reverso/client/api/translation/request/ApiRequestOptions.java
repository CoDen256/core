package coden.reverso.client.api.translation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents json mapping for the request options that will be passed to
 * {@link ApiRequest} to obtain translations
 *
 * @author Denys Chernyshov
 */
public class ApiRequestOptions {
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

    public static class Builder {
        private final ApiRequestOptions apitranslationrequestoptions;
        public Builder(){
            apitranslationrequestoptions = new ApiRequestOptions();
        }

        public Builder setOrigin(String origin) {
            apitranslationrequestoptions.setOrigin(origin);
            return this;
        }

        public Builder enableSentenceSplitter() {
            apitranslationrequestoptions.setSentenceSplitter(true);
            return this;
        }

        public Builder enableContextResults() {
            apitranslationrequestoptions.setContextResults(true);
            return this;
        }

        public Builder enableLanguageDetection() {
            apitranslationrequestoptions.setLanguageDetection(true);
            return this;
        }

        public ApiRequestOptions create(){
            return apitranslationrequestoptions;
        }
    }
}
