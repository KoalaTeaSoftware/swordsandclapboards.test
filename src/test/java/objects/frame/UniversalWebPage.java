package objects.frame;

import helpers.Reports;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

/**
 * A data object that provides access to elements and actions that are common to all web pages
 * Generally, you will want to extend this class when defining you own app's pages. You will then have to
 * 1) Override the locator for the element which you think illustrates that the whole page is drawn (maybe a footer)
 * 2) You may want to override the constructor, for example if you are going to use Selenium's page factory
 */
public class UniversalWebPage {
    /**
     * Constructor - You will probably want to use this version of the constructor for your own kind of web page
     *
     * @param driver     - the WebDriver that is to be used to initialise this object
     * @param diagnostic - use this to determine how the constructor decides when the page is fully drawn
     */
    public UniversalWebPage(WebDriver driver, By diagnostic) {
        this.myDriver = driver;
        waitForPageCompletion(diagnostic);
    }

    /**
     * Default constructor - Will succeed if it can find the body tag - unclear if this means a complete tag with closure
     *
     * @param driver - the WebDriver that is to be used to initialise this object
     */
    public UniversalWebPage(WebDriver driver) {
        this.myDriver = driver;
        waitForPageCompletion(By.tagName("BODY"));
    }

    // Retain a private copy of the driver used to initialise this page - it may not be the default driver
    private final WebDriver myDriver;


    private void waitForPageCompletion(By diagnosticElement) {
        new WebDriverWait(
                myDriver,
                Duration.ofSeconds(Context.pageLoadWait))
                // use the 'presence', i.e. is the element actually in the DOM - it may not be visible
                .until(ExpectedConditions.presenceOfElementLocated(diagnosticElement));
        // OK, Selenium is supposed to do this, but let's make it specific
        waitForJavaScriptReadyStateComplete(Context.pageLoadWait);
    }

    public String readPageTitle() {
        return myDriver.getTitle();
    }

    public String readFirstHeader() {
        return myDriver.findElement(By.tagName("H1")).getText();
    }

    public URL readCurrentLocation() throws MalformedURLException {
        return new URL(myDriver.getCurrentUrl());
    }

    /**
     * Ask the browser if it actually managed to retrieve and draw an image
     *
     * @param imgTag - the web element that is the img tag
     * @return - true => it seems satisfactory
     */
    public boolean browserShowsImage(WebElement imgTag) {
        Object result = ((JavascriptExecutor) myDriver).executeScript(
                "return arguments[0].complete && " +
                        "typeof arguments[0].naturalWidth != \"undefined\" && " +
                        "arguments[0].naturalWidth > 0", imgTag);

        boolean loaded = false;
        if (result instanceof Boolean) {
            loaded = (Boolean) result;
        }
        return loaded;
    }

    public List<WebElement> listImgTags() {
        return myDriver.findElements(By.tagName("IMG"));
    }

    /**
     * When looking at web pages, the implicit wait may not be sufficient,
     * so this explicitly asks the browser if it thinks it has got everything
     * <p>
     * Even this may be less help than you like, so it may be good to override it in your own page object definitions with
     * an explicit wait for the visibility of something that you know is slow for example
     * <p>
     * WebDriverWait wait = new WebDriverWait(driver, 10);
     * WebElement elem = driver.findElement(By.id("diagnosticElement"));
     * wait.until(ExpectedConditions.visibilityOf(elem));
     *
     * @param maxWaitSeconds -
     */
    public void waitForJavaScriptReadyStateComplete(int maxWaitSeconds) {
        if (maxWaitSeconds == 0)
            return; // don't even create the executor

        String state = "";
        JavascriptExecutor js = (JavascriptExecutor) Context.defaultActor.getDriver();

        if (js != null)
            for (int i = 0; i < maxWaitSeconds; i++) {
                try {
                    state = js.executeScript("return document.readyState").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    Reports.writeToHtmlReport("Failed to get document state " + e.getMessage());
                    // Right at the beginning, if the browser has got nothing yet.
                    // we may hit "org.openqa.selenium.JavascriptException: javascript error: Cannot read property 'outerHTML' of null"
                    // In this case, we do not stop waiting.
                }
                if (state.equals("complete")) {
                    return;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                        // don't care
                    }
                }
            }
        else {
            Reports.writeToHtmlReport("[warning] Unable to execute JavaScript to determine if page has loaded");
        }
    }
}
