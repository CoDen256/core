package coden.reverso.data.context;

import coden.reverso.language.ReversoLanguage;

import java.util.stream.Stream;

public interface ReversoContextClient {
    Stream<ReversoContext> getContexts(ReversoLanguage source, ReversoLanguage target, String phrase) throws Exception;
}
