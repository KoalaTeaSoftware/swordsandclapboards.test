package helpers.api;

import java.util.List;

public class PostRequest extends Request {
    /**
     * An extension for convenience's sake
     *
     * @param endpoint - required - if you have any query parameter, append them first
     * @param body     - required - (XML for SOAP and Json for REST) - build it first
     * @param headers  - optional, could be null
     */
    public PostRequest(String endpoint, String body, List<List<String>> headers) {
        super(endpoint, body, headers, "POST");
    }
}
