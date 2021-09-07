package steps.frame;

import helpers.api.Request;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objects.frame.Context;
import org.junit.Assert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class UniversalApiSteps {
    /**
     * The general use of these steps will be to set up the requests URL, headers, body, and then to send the response
     * The sending is where the Request object is actually created (from the previous calls to set up the attributes)
     */
    private String requestMethod = null;
    private List<List<String>> headers = null;
    private String body = null;
    private String url = null;


    @And("the request has following header data")
    public void theRequestHasFollowingHeaderData(DataTable dataTable) {
        /*
        Whilst the header information is expressed as K,V pairs (for better understanding by the writer), we don't
        care right now. The server will be interested in meaning, we just pass it on.
         */
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        //noinspection unchecked,rawtypes
        headers = new ArrayList();

        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            List<String> temp = Arrays.asList(entry.getKey(), entry.getValue());
            headers.add(temp);
        }
        System.out.println("Step-level header list is:" + headers + ":");
    }

    @Given("the request has following simple JSON body elements")
    public void theRequestHasFollowingSimpleJSONBodyElements(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        /*
        The body is being handled and passed around as a string, but should be JSON
        This step is assuming that you want it to be a simple json structure containing the top-level elements that you give
         */
        Map<String, Object> params = new LinkedHashMap<>();

        // I am using this (less efficient code) becuase it is more self-explanatory that the (more efficient) suggestions
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            //noinspection UseBulkOperation
            params.put(entry.getKey(), entry.getValue());
        }

        StringBuilder postData = new StringBuilder();
        try {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            body = postData.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("[info] Step-level body contains:" + body + ":");
    }

    @And("the request has the method {string}")
    public void theRequestHasTheMethod(String method) {
        String verb = method.toUpperCase();
        switch (verb) {
            case "GET":
            case "POST":
            case "PUT":
            case "PATCH":
            case "DELETE":
                requestMethod = verb;
                break;
            default:
                Assert.fail("Unable to process the method :" + verb + ":");
        }
    }

    @And("the request has the url {string}")
    public void theRequestHasTheUrl(String givenUrl) {
        url = givenUrl;
    }

    @When("the request is sent")
    public void theRequestIsSent() {
        // set up the current request (in the Context), and send that request, storing the result
        Context.currentRequest = new Request(url, body, headers, requestMethod);
        Context.currentRequest.sendRequest();
        Context.currentResponseCode = Context.currentRequest.getResponseCode();
    }


//    @When("I {string} from/to api at {string}")
//    public void iFromApiAt(String verb, String location) {
//        switch (verb.toLowerCase()) {
//            case "get":
//                Context.currentRequest = new GetRequest(location, null);
//                break;
//            case "post":
//                Context.currentRequest = new PostRequest(location, null, null);
//                break;
//            case "put":
//                Context.currentRequest = new PutRequest(location, null, null);
//                break;
//            case "patch":
//                myRequest = new PatchRequest(location, null, null);
//                break;
//            case "delete":
//                myRequest = new DeleteRequest(location, null);
//                break;
//            default:
//                Assert.fail("Unable to process the method :" + verb + ":");
//        }
//        response = myRequest.sendRequest();
//        responseCode = myRequest.getResponseCode();
//    }

    @Then("the the response status is {int}")
    public void theTheResponseStatusIs(int expected) {
        Assert.assertEquals("Response code unexpected", expected, Context.currentResponseCode);
    }

    @And("the response {string} header contains {string}")
    public void theResponseHeaderContains(String headerName, String toFind) {
        String haystack = Context.currentRequest.getResponseHeader(headerName);
        String needle = encodeStringToFind(toFind);

        Assert.assertTrue(
                "Should be able to find:" + needle + ": in :" + haystack + ":",
                haystack.contains(needle)
        );
    }

    @And("the response {string} header does not contain {string}")
    public void theResponseHeaderDoesNotContain(String headerName, String toFind) {
        String haystack = getResponseHeaderText(headerName);
        String needle = encodeStringToFind(toFind);

        Assert.assertFalse(
                "Should NOT be able to find:" + needle + ": in :" + haystack + ":",
                haystack.contains(needle)
        );
    }

    /**
     * Try to find a header on the response that answers to the name that uou give. Will fail nicely
     *
     * @param headerName - what to look for
     * @return - what was found
     */
    private String getResponseHeaderText(String headerName) {
        try {
            return "WTF"; //response.header(headerName).toLowerCase();
        } catch (NullPointerException e) {
//            Reports.writeToHtmlReport("[info] All header from the response:\n" + response.headers().toString());
            Assert.fail("It is likely that the header :" + headerName + ": is not present in the response.");
        }
        return "";
    }

    /**
     * It is most likely that the header's text will be URL encoded, therefore URL encode the search string too.  Will fail nicely
     *
     * @param toFind - the string to be encoded
     * @return - the encoded version of the input
     */
    private String encodeStringToFind(String toFind) {
        try {
            return URLEncoder.encode(toFind, String.valueOf(StandardCharsets.UTF_8)).toLowerCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Assert.fail("Internal error encoding the search string.");
        }
        return "";
    }


}
