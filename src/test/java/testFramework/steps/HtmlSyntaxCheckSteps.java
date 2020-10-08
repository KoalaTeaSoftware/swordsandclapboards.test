package testFramework.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import testFramework.objects.W3cHtmlChecker;

public class HtmlSyntaxCheckSteps {
    private W3cHtmlChecker w3cHtmlValidator = null;
    private String url;

    @Given("the w3C HTML tester reviews the file {string}")
    public void theW3CHTMLTesterReviewsTheFile(String urlOfFile) {
        this.url = urlOfFile;
        w3cHtmlValidator = new W3cHtmlChecker(urlOfFile);
    }

    @Then("the w3c HTML tester reports compliance")
    public void theW3CHTMLTesterReportsCompliance() {
        Assert.assertTrue("This :" + this.url + ": (unescaped) should be syntactically acceptable", w3cHtmlValidator.fileValidates());
    }
}
