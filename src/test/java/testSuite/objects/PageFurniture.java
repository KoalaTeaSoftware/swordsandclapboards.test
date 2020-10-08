package testSuite.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;

import java.time.Duration;

/**
 * All of the pages of this web site have common furniture (nav bar, contents-zone, footer)
 */
public class PageFurniture {
    final By mainNav = By.xpath("//*[@id='mainNav']//*[contains(@class,'navbar-nav')]");
    final By lastElement = By.id("footer");

    /**
     * @param driver - asks for this in case you have a driver other than the default driver
     */
    public PageFurniture(WebDriver driver) {
        myDriver = driver;
        new WebDriverWait(Context.driver, Duration.ofSeconds(Context.pageLoadWait))
                // use the 'presence', i.e. is the element actually in the DOM? - expect it to not be visible in plenty of cases
                .until(ExpectedConditions.presenceOfElementLocated(lastElement));
    }

    /**
     * Not the use of normalize() - t is most likely that the user's view of the link text is a noralised string,
     * but the IDE may have munged it (to no end effect - except for space chars sent)
     *
     * @param displayedText - case sensitive the text that the user sees
     * @return - the first element in the main nav bar that IS the text given in the param
     */
    public WebElement getNavItem(String displayedText) {
        // byLinkText consistently fails to find the these links, but the following is fast enough,and reliable
        return myDriver.findElement(mainNav).findElement(By.xpath("//a[normalize-space(text())='" + displayedText + "']"));
    }

    private final WebDriver myDriver;
}
