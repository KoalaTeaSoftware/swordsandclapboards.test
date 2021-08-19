package testFramework.helpers;

import static testFramework.helpers.Reports.writeToHtmlReport;

public class softAssert {
    private final String setName;
    private boolean iHaveSeenAFailure;

    public softAssert() {
        this.iHaveSeenAFailure = false;
        this.setName = "Unnamed";
    }

    public softAssert(String setName) {
        this.iHaveSeenAFailure = false;
        this.setName = setName;
    }

    public void assertAll() {
        assertTrue(!iHaveSeenAFailure, "One of the assertion for this set (" + setName + ") failed");
    }

    public void assertTrue(boolean flag, String s) {
        if (!flag) {
            this.iHaveSeenAFailure = true;
            writeToHtmlReport(s);
        }
    }
}
