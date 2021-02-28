package coden.reverso.client.api.translation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a json mapping for context response entry.
 *
 * @author Denys Chernyshov
 */
public class ApiContextEntryResponse {
    @JsonProperty("translation")
    private String translation;

    @JsonProperty("sourceExamples")
    private List<String> sourceExamples;

    @JsonProperty("targetExamples")
    private List<String> targetExamples;

    @JsonProperty("partOfSpeech")
    private String partOfSpeech;

    @JsonProperty("isRude")
    private boolean isRude;

    @JsonProperty("colloquial")
    private boolean isColloquial;

    private ApiContextEntryResponse() {
    }

    public String getTranslation() {
        return translation;
    }

    public List<String> getSourceExamples() {
        return sourceExamples;
    }

    public List<String> getTargetExamples() {
        return targetExamples;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public boolean isRude() {
        return isRude;
    }

    public boolean isColloquial() {
        return isColloquial;
    }
}
