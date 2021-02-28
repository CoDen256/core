package coden.reverso.client.api.translation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a reverso api response json mapping. It contains the original input
 * the translations as well as the contexts.
 *
 * @author Denys Chernyshov
 */
public class ApiResponse {

    @JsonProperty("input")
    private List<String> input;

    @JsonProperty("translation")
    private List<String> translation;

    //TODO: json path
    @JsonProperty("contextResults")
    private ApiContextResultsResponse contextResults;


    private ApiResponse() {
    }

    public List<String> getInput() {
        return input;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public ApiContextResultsResponse getContextResults() {
        return contextResults;
    }
}
