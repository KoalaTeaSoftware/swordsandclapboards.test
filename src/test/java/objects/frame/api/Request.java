package objects.frame.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@SuppressWarnings("unused")
public class Request {
    private final String endpoint;
    private final List<List<String>> headers;
    private final String requestType;
    private String body = "";

    private JSONObject jsonBody;

    private String authentication = "";
    private String username;
    private String password;
    private String cookie = "";
    private int responseCode;
    private Map<String, List<String>> responseHeaders;
    private String responseString;

    private HttpURLConnection conn;

    /**
     * A set of getters
     */

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseString() {
        return responseString;
    }

    public String getCookie() {
        return this.cookie;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.responseHeaders;
    }

    public String getResponseHeader(String name) {
        try {
            return this.responseHeaders.get(name).toString();
        } catch (java.lang.NullPointerException e) {
            System.out.println("[info] Unable to find a header called :" + name + ":");
            return "";
        }
    }

    /**
     * Set the authorisation up so that it is used when you send the request.
     *
     * @param type     - ["Basic" | "Bearer" ] anything else may not result in what you want
     * @param username - what you think
     * @param password - what you think
     */
    public void setAuthentication(String type, String username, String password) {
        this.authentication = type;
        this.username = username;
        this.password = password;
    }


    /**
     * Constructor - when you are going to send a message, use this to set it up, then use send to actually do something
     *
     * @param endpoint    - needs to be a URL
     * @param body        -  a stringed-up blob of json is going to work
     * @param headers     - make sure that you have something that forces the body to go (like application/x-www-form-urlencoded)
     * @param requestType - One of the normal set - not verified in this code
     */
    public Request(String endpoint, String body, List<List<String>> headers, String requestType) {
        this.endpoint = endpoint;
        this.body = body;
        this.requestType = requestType;
        this.headers = headers;
    }

    /**
     * Constructor - when you are going to send a message, use this to set it up, then use send to actually do something
     *
     * @param endpoint    - needs to be a URL
     * @param jsonBody    -  a stringed-up blob of json is going to work
     * @param headers     - make sure that you have something that forces the body to go (like application/x-www-form-urlencoded)
     * @param requestType - One of the normal set - not verified in this code
     */
    public Request(String endpoint, JSONObject jsonBody, List<List<String>> headers, String requestType) {
        this.endpoint = endpoint;
        this.jsonBody = jsonBody;
        this.requestType = requestType;
        this.headers = headers;
    }


    /**
     * Uses the attributes that you set up when you created the Request (and the auth, if you set it)
     * <p>
     * It is very worth noting that if you do NOT set  | content-type | application/x-www-form-urlencoded |
     * Then the body WILL NOT sent to the victim.
     * <p>
     * Use the getters to see what actually happened.
     */
    public void sendRequest() {
        try {
            java.net.URL url = new URL(endpoint);
            // prevent any messing, we want. this must be set before you open the connection
            HttpURLConnection.setFollowRedirects(false);
            conn = (HttpURLConnection) url.openConnection();
            // this has something to do with the permissions stuff, I think
            conn.setAllowUserInteraction(true);
            // all but PATCH are interested in this
            conn.setRequestMethod(requestType);

            if (headers != null) {
                for (List<String> property : headers) {
                    // use set (rather than add) to make it overwrite any existing defaults, and also
                    // finish up with only the last listing of any specific header
                    conn.setRequestProperty(property.get(0), property.get(1));
                }
            }

            //checks for authentication settings
            if (authentication.equals("Basic")) {
                String userPass = username + ":" + password;
                String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userPass.getBytes());

                conn.setRequestProperty("Authorization", basicAuth);
            } else if (authentication.equals("Bearer")) {
                conn.setRequestProperty("Authorization", "Bearer " + username);
            }

            if (requestType.equalsIgnoreCase("PATCH")) {
                doPatchRequest();
            } else {
                if (jsonBody != null) {
                    body = helpers.Json.toFormEncoded(jsonBody);

                    // this makes the connection accept data
                    conn.setDoOutput(true);

                    // now write to the connected output stream
                    OutputStream os = conn.getOutputStream();
                    byte[] input = body.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                    os.close();
                } else {
                    // this makes it not expect a body (so less likely to fail?)
                    conn.setDoOutput(false);
                }

                // this is where the call is actually made
                this.responseCode = conn.getResponseCode();
                this.cookie = conn.getHeaderField("Set-Cookie");
                this.responseHeaders = conn.getHeaderFields();

                extractResponseString();

                conn.disconnect();
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("[error] Response code: " + responseCode + ":");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * A PATCH need to be done in a different way. NOTE: this is untested code
     *
     * @throws URISyntaxException - if you have given it a dodgy endpoint
     * @throws IOException        - if it can't interpret what it got back
     */
    private void doPatchRequest() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch(new URI(endpoint));
        StringEntity params = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPatch.setEntity(params);
        CloseableHttpResponse response = httpClient.execute(httpPatch);
        HttpEntity entity = response.getEntity();

        this.responseCode = response.getStatusLine().getStatusCode();

        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println("PATCH response:  " + responseString);

        this.responseString = responseString;
    }

    /**
     * Get the response string out of anything other than a patch
     * <p>
     * Only provides anything useful if the response code is one of a few success-type codes
     * Otherwise, it sets the response string using the error sent by the server
     * <p>
     * Also, it handles the IO exception (that is raised if the response was 500),
     * and, in such a case, sets the string to be ""
     */
    private void extractResponseString() {
        try {
            switch (responseCode) {
                case HttpURLConnection.HTTP_CREATED:
                case HttpURLConnection.HTTP_OK:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    // these are response codes that signify some sort of success
                    if (conn.getHeaderField("Content-Encoding") != null && conn.getHeaderField("Content-Encoding").contains("gzip")) {
                        this.responseString = readFromStream(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
                    } else {
                        this.responseString = readFromStream(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                    }
                    break;
                default:
                    InputStreamReader is = new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8);
                    this.responseString = readFromStream(is);
                    System.out.println("[info] Possible error :" + this.responseString + ":");
                    break;
            }
        } catch (java.io.IOException e) {
            // unable to open the stream that might be relevant given the kind of response code
            this.responseString = "";
            System.out.println("[info] Response string is :" + responseString + ": because:" + e.getMessage() + ":");
        }
    }

    private String readFromStream(InputStreamReader is) throws IOException {
        BufferedReader br = new BufferedReader(is);
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        return response.toString();
    }
}
