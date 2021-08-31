package steps.frame;

import helpers.Reports;
import io.cucumber.java.en.And;

public class Reporting {
    @And("I write to the report {string}")
    public void iWriteToTheReport(String message) {
        Reports.writeToHtmlReport(message);
    }
}
