package coden.reverso.client.queryservice.context.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReversoContextResponse {
    @JsonProperty("list")
    private List<ReversoContextEntryResponse> list;

    public ReversoContextResponse() {
    }

    public List<ReversoContextEntryResponse> getList() {
        return list;
    }

    public void setList(List<ReversoContextEntryResponse> list) {
        this.list = list;
    }

}
