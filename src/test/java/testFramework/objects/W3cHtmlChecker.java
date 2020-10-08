package testFramework.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;
import testFramework.actors.Actor;

import java.time.Duration;

public class W3cHtmlChecker {
    private String sut;

    /**
     * It is best to aim this directly at the single files that you create.
     * For example, Bootstrap's css invokes error messages (false negatives?) from this tester.
     *
     * @param urlOfHtmlFile - make it a single file.Scheme is not necessary
     */
    public W3cHtmlChecker(String urlOfHtmlFile) {
        sut = "https://html5.validator.nu/?doc=";
        sut += urlOfHtmlFile;
        sut += "&parser=html";

        Context.defaultActor.getResource(sut);

        new WebDriverWait(Context.driver, Duration.ofSeconds(20)).
                until(ExpectedConditions.presenceOfElementLocated(By.className("details"))
                );
    }

    /**
     * On both the success and failure pages, the first h3 tells you the result
     *
     * @return - whether it contains text that indicates success, or failure
     */
    public Boolean fileValidates() {
        String resultString;
        try {
            resultString = Context.driver.findElement(By.className("success")).getText();
        } catch (NoSuchElementException e) {
            Actor.writeToHtmlReport("Unable to find a success notice from :" + sut + ":");
            return false;
        }

        /*
        <p class="success">The document validates according to the specified schema(s).</p>
         */
        if (resultString.contains(" document is valid ")) {
            return true;
        }
        // make the default result to be failure
        Actor.writeToHtmlReport("Found no evidence of success using :" + sut + ":");
        return false;
    }
}