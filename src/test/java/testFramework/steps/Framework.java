package testFramework.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import testFramework.helpers.Reports;

public class Framework {

    @Given("I write to the html report {string}")
    public void iWriteToTheHtmlReport(String arg0) {
//        System.out.println(arg0);
        Reports.writeToHtmlReport(arg0);
    }

    @Given("I fail a test")
    public void iFailATest() {
        Assert.fail("A deliberately failing test");
    }

    @Then("I force a screen grab to the html report {string}")
    public void iForceAScreenGrabToTheHtmlReport(String arg0) {
        Reports.writeScreenShotToHtmlReport(arg0);
    }
}
