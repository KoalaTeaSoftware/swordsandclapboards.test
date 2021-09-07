package helpers.api;

import java.util.List;

public class PatchRequest extends Request {
    /**
     * An extension for convenience's sake
     * A PATCH differs from a PUT, in that you give instructions of how the resource is to be updated. This is likely to
     * be the different fields in the body, with the expectation that those are altered and the rest remain.
     *
     * @param endpoint - required - if you have any query parameter, append them first
     * @param body     - required - (XML for SOAP and Json for REST) - build it first
     * @param headers  - optional, could be null
     */
    public PatchRequest(String endpoint, String body, List<List<String>> headers) {
        super(endpoint, body, headers, "PATCH");
    }
}
