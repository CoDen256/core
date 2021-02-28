package coden.reverso.highlight;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents helper class to cut(replace with "") given string by given pattern.
 * Each time string is cut, inner state(string) is changed. And the cut points is stored
 * in {@link CuttableText#cuts}
 *
 * @author Denys Chernyshov
 */
public class CuttableText {
    /** The inner text, that is being cut */
    private String text;
    /** The original string that is not cut */
    private final String originalString;
    /** The indexes within the string, where the string was cut */
    private final List<Integer> cuts = new LinkedList<>();

    /**
     * Creates a new {@link CuttableText} with the given string
     *
     * @param originalString
     *         the original string
     */
    public CuttableText(String originalString) {
        this.text = originalString;
        this.originalString = originalString;
    }

    /**
     * The current cut text.
     *
     * @return the current state of the inner text.
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the original String provided at the beginning.
     *
     * @return the original string.
     */
    public String getOriginalString() {
        return originalString;
    }

    /**
     * Cuts the given string by the given pattern, replacing only first
     * occurrence of the given pattern and saves the place of cut.
     *
     * @param pattern
     *         the pattern to cut.
     */
    public void cutAndSave(String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if (matcher.find()){
            cuts.add(matcher.start());
        }
        cut(pattern);
    }

    /**
     * Cuts the given string by the given pattern, replacing only first
     * occurrence of the given pattern. The cut point is not saved.
     *
     * @param pattern
     *         the pattern to cut.
     */
    public void cut(String pattern) {
        text = text.replaceFirst(pattern, "");
    }

    /**
     * Cuts in all places by the pattern and save cut points
     *
     * @param pattern
     *         the pattern to cut.
     */
    public void cutAllAndSave(String pattern) {
        while(Pattern.compile(pattern).matcher(text).find()){
            cutAndSave(pattern);
        }
    }

    /**
     * Cuts string in every place by pattern and DOES NOT save any cut points;
     *
     * @param pattern
     *         the pattern to be cut.
     */
    public void cutAll(String pattern) {
        text = text.replaceAll(pattern, "");
    }

    /**
     * Returns all the cut points that were made by corresponding methods.
     *
     * @return the list of integers.
     */
    public List<Integer> getCutPoints() {
        return cuts;
    }

    /**
     * Clears all the cut points that were made.
     */
    public void resetCutPoints() {
        cuts.clear();
    }

    /**
     * Resets the inner state to the original. Removes all the cut points and sets
     * the inner text to the original one.
     */
    public void reset() {
        resetCutPoints();
        text = originalString;
    }
}
