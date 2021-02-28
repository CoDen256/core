package coden.reverso.client.api.translation.response;

import coden.reverso.data.translation.ReversoTranslation;

import java.util.stream.Stream;

public class ReversoTranslationResponseMapper {

    public ReversoTranslationResponseMapper() {
    }

    public Stream<ReversoTranslation> map(ReversoTranslationResponse response) {
        return response.getTranslation().stream()
                .map(ReversoTranslation::new);
    }

}