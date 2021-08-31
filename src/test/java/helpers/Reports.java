package helpers;

import objects.frame.Context;
import org.apache.commons.text.StringEscapeUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Reports {
    /**
     * @param label - what you want to see written in the report
     */
    public static void writeScreenShotToHtmlReport(String label) {
        try {
            TakesScreenshot ts = (TakesScreenshot) Context.defaultActor.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            Context.currentScenario.attach(screenshot, "image/png", label);
        } catch (Exception e) {
            writeToHtmlReport("Unable to capture the screenshot: " + e.getMessage());
        }
    }

    public static void writePageSourceToHtmlReport() {
        writeToHtmlReport(StringEscapeUtils.escapeHtml4(Context.defaultActor.getDriver().getPageSource()));
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
