package helpers;

import org.json.JSONObject;
import org.junit.Assert;

import java.net.URLEncoder;
import java.util.Iterator;

public class Json {
    /**
     * @param in - Most likely the JSON-shaped body that you would like to send to a service
     * @return A string that works with | content-type | application/x-www-form-urlencoded |
     */
    public static String toFormEncoded(JSONObject in) {
        try {
            StringBuilder out = new StringBuilder();

            Iterator<String> x = in.keys();
            while (x.hasNext()) {
                String key = x.next();
                String value = in.get(key).toString();
                if (out.length() > 0)
                    out.append("&");
                out.append(URLEncoder.encode(key, "UTF-8"));
                out.append('=');
                out.append(URLEncoder.encode(value, "UTF-8"));
            }
            return out.toString();
        } catch (Exception e) {
            Assert.fail("[error] Unexpected crash:" + e.getMessage() + ":");
        }
        return "This is needed for code compliance";
    }
}
