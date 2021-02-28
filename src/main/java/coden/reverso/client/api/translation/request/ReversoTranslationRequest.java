package coden.reverso.client.api.translation.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReversoTranslationRequest {
    @JsonProperty("input")
    private String input;

    @JsonProperty("from")
    private String sourceLanguage;

    @JsonProperty("to")
    private String targetLanguage;

    @JsonProperty("format")
    private String format;

    @JsonProperty("options")
    private ReversoTranslationOptions options;


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

    public ReversoTranslationOptions getOptions() {
        return options;
    }

    public void setOptions(ReversoTranslationOptions options) {
        this.options = options;
    }
}
