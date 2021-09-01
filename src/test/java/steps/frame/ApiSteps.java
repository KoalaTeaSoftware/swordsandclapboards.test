package steps.frame;

import helpers.Reports;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;


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

    @And("the response {string} header contains {string}")
    public void theResponseHeaderContains(String headerName, String toFind) {
        try {
            String haystack = response.header(headerName).toLowerCase();
            Assert.assertTrue(
                    "Unable to find a header called :" + headerName + ":",
                    haystack.length() > 5);

            String errorMessage = URLEncoder.encode(toFind, String.valueOf(StandardCharsets.UTF_8)).toLowerCase();

            Assert.assertTrue(
                    "Should be able to find:" + errorMessage + ": in :" + haystack + ":",
                    haystack.contains(errorMessage)
            );
        } catch (UnsupportedEncodingException e) {
            Assert.fail("Internal error munging the headers of the response");
            e.printStackTrace();
        } catch (NullPointerException e) {
            Reports.writeToHtmlReport("All header from the response:\n" + response.headers().toString());
            Assert.fail(
                    "It is likely that the header :" + headerName + ": is not present in the response.");
        }
    }


    @Given("I post the following data to the api at {string}")
    public void iPostTheFollowingDataToTheApiAt(String location, DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

        Iterator<Map.Entry<String, String>> iterator = dataMap.entrySet().iterator();

        request.header("Accept", ContentType.JSON.getAcceptHeader());
        request.urlEncodingEnabled(true);

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
            request.param(entry.getKey(), entry.getValue());
        }

        response = request.post(location);
    }
}
