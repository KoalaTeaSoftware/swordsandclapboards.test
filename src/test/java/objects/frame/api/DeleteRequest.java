package objects.frame.api;

import java.util.List;

public class DeleteRequest extends Request {
    /**
     * An extension for convenience
     * @param endpoint - required - If that has parameters (or query parameters) add them first
     * @param headers - optional, could be null
     */
    public DeleteRequest(String endpoint, List<List<String>> headers) {
        super(endpoint, null, headers, "DELETE");
    }
}
