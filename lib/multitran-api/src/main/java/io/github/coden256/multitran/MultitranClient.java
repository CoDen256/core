package io.github.coden256.multitran;

import io.github.coden256.multitran.context.MultitranContextClient;
import io.github.coden256.multitran.translation.MultitranTranslationClient;

/**
 * Represents general client to fetch all kinds of information from the multitran.com
 *
 * @author Denys Chernyshov
 */
public interface MultitranClient extends MultitranTranslationClient, MultitranContextClient {
}
