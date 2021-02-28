package coden.reverso.client.queryservice.context.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReversoContextEntryResponse {
    @JsonProperty("s_text")
    private String sourceSentence;
    @JsonProperty("t_text")
    private String targetSentence;

    public String getSourceSentence() {
        return sourceSentence;
    }

    public String getTargetSentence() {
        return targetSentence;
    }

    public void setSourceSentence(String sourceSentence) {
        this.sourceSentence = sourceSentence;
    }

    public void setTargetSentence(String targetSentence) {
        this.targetSentence = targetSentence;
    }
}
