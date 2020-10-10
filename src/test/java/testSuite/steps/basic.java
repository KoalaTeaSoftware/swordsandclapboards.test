package testSuite.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import testFramework.Context;
import testSuite.objects.PageSwordsAndClapboards;

public class basic {
    @Given("the page is fully drawn")
    public void thePageIsFullyDrawn() {
        // the constructor for this sort of object will wait until it thinks that the page is complete
        new PageSwordsAndClapboards(Context.driver);
    }

    @When("I click on the nav link with text {string}")
    public void iClickOnTheNavLinkWithText(String linkText) {
        PageSwordsAndClapboards pageSwordsAndClapboards = new PageSwordsAndClapboards(Context.driver);
        WebElement linkElement = pageSwordsAndClapboards.getNavItem(linkText);

        Assert.assertTrue("The '" + linkText + "' link should be visible and enabled",
                linkElement.isDisplayed() && linkElement.isEnabled()
        );
        linkElement.click();
    }
}
