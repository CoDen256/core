package io.github.coden256.reverso;

import io.github.coden256.reverso.data.context.ReversoContextClient;
import io.github.coden256.reverso.data.translation.ReversoTranslationClient;

/**
 * Represents a general interface covering all other clients for simplification.
 *
 * @author Denys Chernyshov
 */
public interface ReversoClient extends ReversoContextClient, ReversoTranslationClient {
}
