package testFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiSteps {
    // We always want to stop RestAssured from following redirects, so that we can see the first response from out victim
    private final RequestSpecification request = RestAssured.given().redirects().follow(false);
    private Response response;

    @When("I {string} from api at {string}")
    public void iFromApiAt(String arg0, String arg1) {
        switch (arg0.toLowerCase()) {
            case "get":
                response = request.get(arg1);
                break;
            case "put":
                response = request.put(arg1);
                break;
            case "delete":
                response = request.delete(arg1);
                break;
            default:
                Assert.fail("Unable to process the method :" + arg0 + ":");
        }
    }

    @Then("the the response status is {int}")
    public void theTheResponseStatusIs(int arg0) {
        response.then().statusCode(arg0);
    }

    @And("the user is sent back the the contact page")
    public void theUserIsSentBackTheTheContactPage() {
        final String needle = "location";
        String haystack = response.header("Location");
        Assert.assertNotNull(
                "There should be a header called :" + needle + ":",
                haystack);
    }

    @And("there is an error message containing {string}")
    public void thereIsAnErrorMessageContaining(String arg0) {
        try {
            String haystack = response.header("Location").toLowerCase();
            String errorIndicator = URLEncoder.encode("errors=", String.valueOf(StandardCharsets.UTF_8)).toLowerCase();
            String errorMessage = URLEncoder.encode(arg0, String.valueOf(StandardCharsets.UTF_8)).toLowerCase();

            Assert.assertTrue(
                    "Should be able to find:" + errorIndicator + ": in :" + haystack + ":",
                    haystack.contains(errorIndicator));

            Assert.assertTrue(
                    "Should be able to find:" + errorMessage + ": in :" + haystack + ":",
                    haystack.contains(errorMessage)
            );
        } catch (UnsupportedEncodingException e) {
            Assert.fail("Internal error munging the headers of the response");
            e.printStackTrace();
        }
    }

}
