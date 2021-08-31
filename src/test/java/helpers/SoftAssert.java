package helpers;

/**
 * Own version of soft asserts - So that you don't feel tempted to complicate life by using TestNG
 * <p>
 * For example, the 'images on a page' test. It is more effective to get a complete list of failures than having to run the test repeatedly.
 */
public class SoftAssert {
    private final String nameOfAssertionSet;
    private boolean iHaveSeenAFailure;

    /**
     * Most normally used constructor
     *
     * @param setName - a nice label that is used in reporting failures
     */
    public SoftAssert(String setName) {
        this.iHaveSeenAFailure = false;
        this.nameOfAssertionSet = setName;
    }

    /**
     * An alternative to making the assertion in this class. You can get the status for use in your own assertion
     *
     * @return - the cumulative state - true if there has been a failure
     */
    public boolean iHaveSeenAFailure() {
        return iHaveSeenAFailure;
    }

    /**
     * Your normal, in-the-loop test - does not, in itself, terminate the scenario
     *
     * @param flag - if the value of this is true, then it has passed this time
     * @param s    - message to be written to the report if the test fails
     */
    public void assertTrue(boolean flag, String s) {
        if (!flag) {
            this.iHaveSeenAFailure = true;
            Reports.writeToHtmlReport(s);
        }
    }

    /**
     * Depending on the cumulative status, could terminate the scenario
     */
    public void assertAll() {
        assertTrue(!iHaveSeenAFailure, "One of the assertion for this set (" + nameOfAssertionSet + ") failed");
    }

}
