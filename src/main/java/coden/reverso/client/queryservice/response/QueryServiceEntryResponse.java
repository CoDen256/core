package coden.reverso.client.queryservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The response entry returned by reverso query service in json format.
 * It is contained in {@link QueryServiceResponse}
 *
 * @author Denys Chernyshov
 */
public class QueryServiceEntryResponse {
    @JsonProperty("s_text")
    private String sourceSentence;
    @JsonProperty("t_text")
    private String targetSentence;

    private QueryServiceEntryResponse() {

    }

    public String getSourceSentence() {
        return sourceSentence;
    }

    public String getTargetSentence() {
        return targetSentence;
    }
}
