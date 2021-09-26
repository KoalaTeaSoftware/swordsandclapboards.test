package steps.frame;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objects.frame.Context;
import objects.frame.UniversalWebNavBar;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class UniversalWebNavSteps {

    private UniversalWebNavBar myNavBar = null;

    private UniversalWebNavBar getMyBar() {
        if (myNavBar == null)
            myNavBar = new UniversalWebNavBar(Context.defaultBrowser.getDriver());
        return myNavBar;
    }


    @Then("the number of nav items is {int}")
    public void theNumberOfNavItemsIs(int expectedCount) {
        Assert.assertEquals(expectedCount, getMyBar().getNavItemCount());
    }


    @When("I click on the nav links with text {string}")
    public void iClickOnTheNavLinksWithText(String navText) {
        WebElement link = getMyBar().getNavItem(navText);
        Assert.assertNotNull(link);
        link.click();
    }
}
