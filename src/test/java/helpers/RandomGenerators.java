package helpers;

import java.security.SecureRandom;

@SuppressWarnings("unused")
public class RandomGenerators {

    /**
     * @param minIn - inclusive
     * @param maxIn - inclusive
     * @return - the random integer
     */
    public static int getRandomInt(int minIn, int maxIn) {
        double min = Math.ceil(minIn);
        double max = Math.floor(maxIn);
        return (int) (Math.floor(Math.random() * (max - min + 1)) + min); //The maximum is inclusive and the minimum is inclusive
    }

    /**
     * @param lengthOfString - number of characters to be inserted
     * @return - a string containing letters from the alphabet, no white-space letters
     */
    public static String createRandomLetters(int lengthOfString) {
        //noinspection SpellCheckingInspection
        return createString(lengthOfString, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }

    /**
     * @param lengthOfString - number of characters to be inserted
     * @return - a string containing letters from the alphabet, **possibly broken** into words using the space character
     */
    public static String createRandomWords(int lengthOfString) {
        //noinspection SpellCheckingInspection
        return createString(lengthOfString, "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz");
    }

    /**
     * Lets you specify the candidate set. Could be special chars, or a smaller set that, say, the words one
     *
     * @param lengthOfString - number of characters to be inserted
     * @param candidates     - your own set of special characters.
     * @return - a string built form randomly picked members of the input candidates
     */
    public static String createRandomString(int lengthOfString, String candidates) {
        return createString(lengthOfString, candidates);
    }

    /**
     * @param lengthOfString - number of characters to be inserted
     * @return - a **string** of digits. No decimal.
     */
    public static String createRandomNumber(int lengthOfString) {
        return createString(lengthOfString, "0123456789");
    }

    /**
     * Pick from a predefined list of special chars to make a string
     *
     * @param lengthOfString - number of characters to be inserted
     * @return a string comprising picked members of our predefined set.
     */
    public static String createRandomSpecialChars(int lengthOfString) {
        return createString(lengthOfString, "!@#$%^&*");
    }

    /**
     * The code written once-only
     *
     * @param lengthOfString- number of characters to be inserted
     * @param candidates      - list of characters from which to pick
     * @return - a string made of the selected chars
     */
    private static String createString(int lengthOfString, String candidates) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(lengthOfString);
        for (int i = 0; i < lengthOfString; i++)
            stringBuilder.append(candidates.charAt(secureRandom.nextInt(candidates.length())));
        return stringBuilder.toString();
    }
}
