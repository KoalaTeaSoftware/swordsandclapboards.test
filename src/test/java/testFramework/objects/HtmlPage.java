package testFramework.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;
import testFramework.actors.Actor;

import java.time.Duration;

public class HtmlPage {
    public String readPageTitle() {
        return myDriver.getTitle();
    }

    public HtmlPage(WebDriver driver) {
        this.myDriver = driver;
        new WebDriverWait(Context.driver, Duration.ofSeconds(Context.pageLoadWait))
                // use the 'presence', i.e. is the element actually in the DOM - it may not be visible
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("BODY")));
        waitForJavaScriptReadyStateComplete(Context.pageLoadWait);
    }

    /**
     * When looking at web pages, the implicit wait may not be sufficient,
     * so explicitly ask the browser if it thinks it has got everything
     * <p>
     * Even this may be less help than you like, so it may be good to override it in your own page object definitions with with
     * an explicit wait for the visibility of something that you know is slow
     * for example
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
                    Actor.writeToHtmlReport("Failed to get document state " + e.getMessage());
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
            System.out.println("[warning] unable to execute JavaScript to determine if page has loaded");
            Actor.writeToHtmlReport("[warning] unable to execute JavaScript to determine if page has loaded");
        }
    }

    public String getFirstHeaderText() {
        return myDriver.findElement(By.tagName("H1")).getText();
    }

    private final WebDriver myDriver;
}
