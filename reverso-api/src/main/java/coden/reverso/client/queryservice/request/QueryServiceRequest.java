package coden.reverso.client.queryservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a request to fetch contexts for reverso.net using query service api
 *
 * @author Denys Chernyshov
 */
public class QueryServiceRequest {

    @JsonProperty("source_text")
    private String sourceText;

    @JsonProperty("target_text")
    private String targetText;

    @JsonProperty("source_lang")
    private String sourceLang;

    @JsonProperty("target_lang")
    private String targetLang;

    @JsonProperty("npage")
    private int pageCount;

    @JsonProperty("mode")
    private int mode;


    public String getSourceText() {
        return sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public String getTargetLang() {
        return targetLang;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getMode() {
        return mode;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public void setTargetLang(String targetLang) {
        this.targetLang = targetLang;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public static class Builder {
        private final QueryServiceRequest request;
        public Builder(){
            request = new QueryServiceRequest();
        }

        public Builder setSourceText(String sourceText) {
            request.setSourceText(sourceText);
            return this;
        }

        public Builder setTargetText(String targetText) {
            request.setTargetText(targetText);
            return this;
        }

        public Builder setSourceLang(String sourceLang) {
            request.setSourceLang(sourceLang);
            return this;
        }

        public Builder setTargetLang(String targetLang) {
            request.setTargetLang(targetLang);
            return this;
        }

        public Builder setPageCount(int pageCount) {
            request.setPageCount(pageCount);
            return this;
        }

        public Builder setMode(int mode) {
            request.setMode(mode);
            return this;
        }

        public QueryServiceRequest create(){
            return request;
        }
    }
}
