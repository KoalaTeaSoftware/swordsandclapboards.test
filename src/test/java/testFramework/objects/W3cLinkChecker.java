package testFramework.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;
import testFramework.actors.Actor;

import java.time.Duration;

public class W3cLinkChecker {
    private String sut;

    /**
     * It is best to aim this directly at the single files that you create.
     * For example, Bootstrap's css invokes error messages (false negatives?) from this tester.
     *
     * @param urlOfCssFile - make it a single file.Scheme is not necessary
     */
    public W3cLinkChecker(String urlOfCssFile) {
        sut = "https://validator.w3.org/checklink?uri=";
        sut += urlOfCssFile;
        sut += "%2F&summary=on&hide_type=all&depth=&check=Check";

        Context.defaultActor.getResource(sut);

        new WebDriverWait(Context.driver, Duration.ofSeconds(120)).until(ExpectedConditions.presenceOfElementLocated(By.tagName("H3")));
    }

    /**
     * @return - whether it contains text that indicates success, or failure
     */
    public Boolean fileValidates() {
        // the first h3 tells you the result
        if (Context.driver.findElement(By.tagName("h3")).getText().toLowerCase().contains("broken links")) {
            Actor.writeToHtmlReport("Found mention of broken links using :" + sut + ":");
            // it definitely says there is a problem
            return false;
        }
        // otherwise, hunt for the p that specifically indicates success
        for (WebElement p : Context.driver.findElements(By.tagName("p"))) {
            if (p.getText().equalsIgnoreCase("Valid links!"))
                return true;
        }
        // failing anything good, default to failure
        Actor.writeToHtmlReport("Found no evidence of success using :" + sut + ":");
        return false;
    }

}