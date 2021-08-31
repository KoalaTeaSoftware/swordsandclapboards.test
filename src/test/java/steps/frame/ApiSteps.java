package steps.frame;

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

    // we will want to share the response, once it has been populated with different steps
    private Response response;

    @When("I {string} from api at {string}")
    public void iFromApiAt(String verb, String location) {
        switch (verb.toLowerCase()) {
            case "get":
                response = request.get(location);
                break;
            case "put":
                response = request.put(location);
                break;
            case "delete":
                response = request.delete(location);
                break;
            default:
                Assert.fail("Unable to process the method :" + verb + ":");
        }
    }

    @Then("the the response status is {int}")
    public void theTheResponseStatusIs(int arg0) {
        response.then().statusCode(arg0);
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
