package coden.multitran;

import coden.multitran.context.MultitranContextClient;
import coden.multitran.translation.MultitranTranslationClient;

/**
 * Represents general client to fetch all kinds of information from the multitran.com
 *
 * @author Denys Chernyshov
 */
public interface MultitranClient extends MultitranTranslationClient, MultitranContextClient {
}
