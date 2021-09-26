package objects.sut;

import objects.frame.UniversalWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * All of the pages of this web site have common furniture (nav bar, contents-zone, footer)
 * It retains a handle on the driver that was used to initialise it (which could be other that the default Actor's driver).
 */
public class CommonSutPage extends UniversalWebPage {
    /**
     * @param driver - asks for this in case you have a driver other than the default driver
     */
    public CommonSutPage(WebDriver driver) {
        super(driver, By.id("footer"));
    }
}
