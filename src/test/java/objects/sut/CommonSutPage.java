package objects.sut;

import objects.frame.UniversalWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * All of the pages of this web site have common furniture (nav bar, contents-zone, footer)
 * It retains a handle on the driver that was used to initialise it (which could be other that the default Actor's driver).
 */
public class CommonSutPage extends UniversalWebPage {
    // For convenience, make a copy of the driver that was used to create this object
    protected final WebDriver myDriver;

    /**
     * @param driver - asks for this in case you have a driver other than the default driver
     */
    public CommonSutPage(WebDriver driver) {
        super(driver, By.id("footer"));
        myDriver = driver;
    }

    // Define locators that are specific to this page object
    final By mainNav = By.xpath("//*[@id='mainNav']//*[contains(@class,'navbar-nav')]");

    ///////////////////////////////////////////////////
    // Define Actions that use these locators

    /**
     * @param displayedText - case sensitive the text that the user sees
     * @return - the first element in the main nav bar that IS the text given in the param
     */
    public WebElement getNavItem(String displayedText) {
        /* Note the use of normalize() - it is most likely that the user's view of the link text is a normalized string,
         * but the IDE may have munged it (to no end effect - except for space chars sent)
         *
         * byLinkText consistently fails to find the these links, but the following is fast enough,and reliable
         */
        return myDriver.findElement(mainNav).findElement(By.xpath("//a[normalize-space(text())='" + displayedText + "']"));
    }

}
