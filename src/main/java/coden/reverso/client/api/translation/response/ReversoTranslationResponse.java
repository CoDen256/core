package coden.reverso.client.api.translation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReversoTranslationResponse {

    @JsonProperty("input")
    private List<String> input;

    @JsonProperty("translation")
    private List<String> translation;

    @JsonProperty("contextResults")
    private ReversoTranslationContextResults contextResults;


    private ReversoTranslationResponse() { }

    public List<String> getInput() {
        return input;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public ReversoTranslationContextResults getContextResults() {
        return contextResults;
    }
}
