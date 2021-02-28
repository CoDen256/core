package coden.multitran.language;

/**
 * The {@link MultitranLanguage} contains all possible supported
 * languages by the multitran and their "url representation" or "multitran standard"
 *
 * @author Denys Chernyshov
 */
public enum MultitranLanguage {
    EN(1), RU(2), DE(3);

    /** The standard used by multitran to represent this languages */
    private final int multitranStandard;

    MultitranLanguage(int multitranStandard) {
        this.multitranStandard = multitranStandard;
    }

    public int getMultitranStandard() {
        return multitranStandard;
    }
}
