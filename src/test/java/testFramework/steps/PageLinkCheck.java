package testFramework.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import testFramework.objects.W3cLinkChecker;

public class PageLinkCheck {
    private W3cLinkChecker w3cLinkChecker;
    private String url;

    @Given("the w3c link checker reviews the file {string}")
    public void theW3CLinkCheckerReviewsTheFile(String urlOfFile) {
        this.url = urlOfFile;
        w3cLinkChecker = new W3cLinkChecker(urlOfFile);
    }

    @Then("the w3c link checker reports compliance")
    public void theW3CLinkCheckerReportsCompliance() {
        Assert.assertTrue("Links on page :" + this.url + ": appear broken", w3cLinkChecker.fileValidates());
    }
}
