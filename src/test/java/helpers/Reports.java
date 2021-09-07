package helpers;

import objects.frame.Context;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Reports {
    /**
     * @param label - Text that you want, in addition to the screen grab, in the report
     */
    public static void writeScreenShotToHtmlReport(String label) {
        try {
            TakesScreenshot ts = (TakesScreenshot) Context.defaultBrowser.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            Context.currentScenario.attach(screenshot, "image/png", label);
        } catch (Exception e) {
            writeToHtmlReport(label);
            writeToHtmlReport("Unable to capture a screenshot: " + e.getMessage());
        }
    }

    /**
     * Capture the page source code and add that in to the HTML report
     * It is escaped so that it should be visible as text, when the report is shown as HTML, not interpreted by the browser.
     */
    public static void writePageSourceToHtmlReport() {
        String pageSource = Context.defaultBrowser.getDriver().getPageSource();
        if (pageSource.length() > 0)
            writeToHtmlReport(StringEscapeUtils.escapeHtml4(pageSource));
        else
            writeToHtmlReport("Unable to capture page source: string is empty");
    }

    public static void writeCurrentResponseToHtmlReport() {
        if (Context.currentResponseString == null) {
            writeToHtmlReport("The current response has not been set");
        } else if (Context.currentResponseString.length() <= 0) {
            writeToHtmlReport("The current response is an empty string");
        } else {
            try {
                JSONObject tempObj = new JSONObject(Context.currentResponseString);
                writeToHtmlReport("Current response:");
                writeToHtmlReport(tempObj.toString(4));
            } catch (JSONException ex) {
                try {
                    JSONArray tempAr = new JSONArray(Context.currentResponseString);
                    writeToHtmlReport("Current response:");
                    writeToHtmlReport(tempAr.toString(4));
                } catch (JSONException ex1) {
                    writeToHtmlReport("Unable to make a nice JSON object, or array from this response:");
                    writeToHtmlReport(StringEscapeUtils.escapeHtml4(Context.currentResponseString));
                }
            }
        }
    }

    /**
     * Just what is says on the tin.
     *
     * @param message - what you ant to see in the report
     */
    public static void writeToHtmlReport(String message) {
        Context.currentScenario.log(message);
    }
}
