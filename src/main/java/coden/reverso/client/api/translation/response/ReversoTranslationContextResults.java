package coden.reverso.client.api.translation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReversoTranslationContextResults {

    @JsonProperty("results")
    private List<ReversoTranslationContextEntry> entries;

    private ReversoTranslationContextResults() {
    }

    public List<ReversoTranslationContextEntry> getEntries() {
        return entries;
    }
}
