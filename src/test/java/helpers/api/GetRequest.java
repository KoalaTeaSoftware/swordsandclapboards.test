package helpers.api;

import java.util.List;

public class GetRequest extends Request {
    /**
     *
     * @param endpoint - required - add any query parameters before you send in this endpoint
     * @param headers - can be set to null
     */
    public GetRequest(String endpoint, List<List<String>> headers) {
        super(endpoint, null, headers, "GET");
    }
}
