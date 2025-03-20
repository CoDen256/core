package coden.reverso.client.queryservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Json mapping for the response returned by reverso query service.
 *
 * @author Denys Chernyshov
 */
public class QueryServiceResponse {
    @JsonProperty("list")
    private List<QueryServiceEntryResponse> list;

    public QueryServiceResponse() {
    }

    public List<QueryServiceEntryResponse> getList() {
        return list;
    }
}
