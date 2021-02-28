package coden.reverso.client.api.translation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a json mapping for the request that will be passed as body
 * to obtain translations from reverso.net
 *
 * @author Denys Chernyshov
 */
public class ApiRequest {

    @JsonProperty("input")
    private String input;

    @JsonProperty("from")
    private String sourceLanguage;

    @JsonProperty("to")
    private String targetLanguage;

    @JsonProperty("format")
    private String format;

    @JsonProperty("options")
    private ApiRequestOptions options;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public ApiRequestOptions getOptions() {
        return options;
    }

    public void setOptions(ApiRequestOptions options) {
        this.options = options;
    }

    public static class Builder {
        private final ApiRequest request;
        public Builder(){
            request = new ApiRequest();
        }

        public Builder setInput(String input) {
            request.setInput(input);
            return this;
        }

        public Builder setSourceLanguage(String sourceLanguage) {
            request.setSourceLanguage(sourceLanguage);
            return this;
        }

        public Builder setTargetLanguage(String targetLanguage) {
            request.setTargetLanguage(targetLanguage);
            return this;
        }

        public Builder setFormat(String format) {
            request.setFormat(format);
            return this;
        }

        public Builder setOptions(ApiRequestOptions options) {
            request.setOptions(options);
            return this;
        }

        public ApiRequest create(){
            return request;
        }
    }
}
