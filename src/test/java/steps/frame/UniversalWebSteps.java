package steps.frame;

import helpers.ResourceLocator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import objects.frame.Context;
import objects.frame.UniversalWebPage;
import org.junit.Assert;

/**
 * This set of steps is available to anything that is going to use this class.
 * Generally, for the pages on your app, extend this type, overwriting
 * 1) the type of the 'myPageObject' with you own extension of the UniversalWebPage class
 * 2) the 'getMyPage()' with your own (because it will be creating an object of your own class)
 */
public class UniversalWebSteps {

    public UniversalWebPage myPageObject;

    /**
     * The page object is lazy-instantiated so that you are not making a new one for each step.
     * This allows you, at base, to use the one instance for all of the steps within a scenario (not re-parsing each time)
     *
     * @return - an instance of the page object
     */
    private UniversalWebPage getMyPage() {
        if (myPageObject == null)
            myPageObject = getNewPage();
        return myPageObject;
    }

    /**
     * If you need to force a new instance of the page object for your step (e.g. you know that the page has changed enormously),
     * then you can use this in your step to create a new, local instance of the page.
     *
     * @return - a new instance of the page object
     */
    private UniversalWebPage getNewPage() {
        return new UniversalWebPage(Context.defaultBrowser.getDriver());
    }

    @Given("I navigate to the page {string}")
    public void iNavigateToThePage(String resourceLocator) {
        String fullLocator = ResourceLocator.interpretURL(resourceLocator);
        Assert.assertNotNull("Unable to make the URL work", fullLocator);
        Context.defaultBrowser.getResource(fullLocator);
    }


    @Then("the page title is {string}")
    public void thePageTitleIs(String expected) {
        if (expected.isEmpty()) {
            try {
                expected = Context.sutConfiguration.getProperty("defaultTitle");
            } catch (NoSuchFieldException e) {
                Assert.fail("The expected title has not been defined in the SUT configuration, and not defined in " +
                        "this test step");
            }
        }
        Assert.assertEquals("The page title is not as expected", expected, getMyPage().readPageTitle());
    }

    @Then("the page title contains {string}")
    public void thePageTitleContains(String expected) {
        if (expected.isEmpty()) {
            try {
                expected = Context.sutConfiguration.getProperty("defaultTitle");
            } catch (NoSuchFieldException e) {
                Assert.fail("The expected title has not been defined in the SUT configuration, and not defined in " +
                        "this test step");
            }
        }
        Assert.assertTrue(
                String.format("The page title :%s: should contain the string :%s:", getMyPage().readPageTitle(), expected),
                getMyPage().readPageTitle().contains(expected)
        );
    }

    @Then("the page title does not contain {string}")
    public void thePageTitleDoesNotContain(String expected) {
        if (expected.isEmpty()) {
            try {
                expected = Context.sutConfiguration.getProperty("defaultTitle");
            } catch (NoSuchFieldException e) {
                Assert.fail("The expected title has not been defined in the SUT configuration, and not defined in " +
                        "this test step");
            }
        }
        Assert.assertFalse(
                String.format("The page title :%s: should NOT contain the string :%s:", getMyPage().readPageTitle(), expected),
                getMyPage().readPageTitle().contains(expected)
        );
    }

    @And("the first heading is {string}")
    public void theFirstHeadingIs(String expected) {
        Assert.assertEquals("Unexpected H1", expected, getMyPage().readFirstHeader());
    }

    @And("the first heading becomes {string}")
    public void theFirstHeadingBecomes(String expected) {
        Assert.assertEquals("Unexpected H1", expected, getMyPage().readFirstHeader());
    }

    @And("the first heading contains {string}")
    public void theFirstHeadingContains(String needle) {
        final String haystack = getMyPage().readFirstHeader();
        Assert.assertTrue(
                "The first header :" + haystack + ": should contain :" + needle + ":",
                haystack.contains(needle));
    }

    @And("the first heading does not contain {string}")
    public void theFirstHeadingDoesNotContain(String needle) {
        final String haystack = getMyPage().readFirstHeader();
        Assert.assertFalse(
                "The first header :" + haystack + ": should NOT contain :" + needle + ":",
                haystack.contains(needle));
    }

}
