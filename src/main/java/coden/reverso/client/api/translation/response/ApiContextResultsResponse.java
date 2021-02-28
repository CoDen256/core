package coden.reverso.client.api.translation.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a list of contexts fetched in {@link ApiResponse}
 *
 * @author Denys Chernyshov
 */
public class ApiContextResultsResponse {

    @JsonProperty("results")
    private List<ApiContextEntryResponse> entries;

    private ApiContextResultsResponse() {
    }

    public List<ApiContextEntryResponse> getEntries() {
        return entries;
    }
}
