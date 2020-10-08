package testFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import testFramework.Context;
import testFramework.actors.Actor;
import testFramework.objects.HtmlPage;

public class basic {

    @Given("I write to the html report {string}")
    public void iWriteToTheHtmlReport(String arg0) {
        System.out.println(arg0);
        Actor.writeToHtmlReport(arg0);
    }

    @Given("I fail a test")
    public void iFailATest() {
        Assert.fail("A deliberately failing test");
    }

    @Given("I navigate to the page {string}")
    public void iNavigateToThePage(String fullUrl) {
        Context.defaultActor.getResource(fullUrl);
    }

    @Then("the page title is {string}")
    public void thePageTitleIs(String expected) {
        HtmlPage page = new HtmlPage(Context.driver);

        Assert.assertEquals("The page title is not as expected", expected, page.readPageTitle());
    }

    @And("the first heading is {string}")
    public void theFirstHeadingIs(String expected) {
        HtmlPage page = new HtmlPage(Context.driver);
        Assert.assertEquals("Unexpected H1", expected, page.getFirstHeaderText());
    }

    @And("the first heading does not contain {string}")
    public void theFirstHeadingDoesNotContain(String needle) {
        HtmlPage page = new HtmlPage(Context.driver);
        boolean haystackDoesContainNeedle = page.getFirstHeaderText().contains(needle);

        Assert.assertFalse(
                "The first header " + page.getFirstHeaderText() + ":should not contain :" + needle + ":",
                haystackDoesContainNeedle);
    }


}
