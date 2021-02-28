package coden.reverso;

import coden.reverso.data.context.ReversoContextClient;
import coden.reverso.data.translation.ReversoTranslationClient;

/**
 * Represents a general interface covering all other clients for simplification.
 *
 * @author Denys Chernyshov
 */
public interface ReversoClient extends ReversoContextClient, ReversoTranslationClient {
}
