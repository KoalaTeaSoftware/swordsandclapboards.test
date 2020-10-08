package testSuite.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import testFramework.Context;
import testSuite.objects.PageFurniture;

public class basic {
    @Given("the page is fully drawn")
    public void thePageIsFullyDrawn() {
        // the constructor for this sort of object will wait until it thinks that the page is complete
        new PageFurniture(Context.driver);
    }

    @When("I click on the nav link with text {string}")
    public void iClickOnTheNavLinkWithText(String linkText) {
        PageFurniture pageFurniture = new PageFurniture(Context.driver);
        WebElement linkElement = pageFurniture.getNavItem(linkText);

        Assert.assertTrue("The '" + linkText + "' link should be visible and enabled",
                linkElement.isDisplayed() && linkElement.isEnabled()
        );
        linkElement.click();
    }
}
