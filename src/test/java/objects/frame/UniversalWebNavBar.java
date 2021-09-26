package objects.frame;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This is a sut-related set, rather than a framework
 */
public class UniversalWebNavBar {

    // Define locators that will be used unless we get told otherwise (in the second constructor)
    private By mainNav = By.id("mainNav");
    private By navItem = By.className("nav-item");

    /**
     * Default constructor - Will succeed if it can find the body tag - unclear if this means a complete tag with closure
     *
     * @param driver - the WebDriver that is to be used to initialise this object
     */
    public UniversalWebNavBar(WebDriver driver) {
        this.myDriver = driver;
    }

    /**
     * A constructor that can be used if you extend this class for a navbar that has different handles
     *
     * @param driver     - the WebDriver that is to be used to initialise this object
     * @param barHandle  - some sort of By object that can find the Web Element that forms the outside box of the nav bar
     *                   - It is best if this is an ID. The First match of this will the one that is used
     * @param itemHandle - a By class object that will match **all of** the nav items within the element identified by the barHandle
     *                   - So, it is best if this is a css class
     */
    public UniversalWebNavBar(WebDriver driver, By barHandle, By itemHandle) {
        this.myDriver = driver;
        this.mainNav = barHandle;
        this.navItem = itemHandle;
    }

    // Retain a private copy of the driver used to initialise this page - it may not be the default driver
    private final WebDriver myDriver;


    /**
     * It is looking for the first anchor tag, within the main nav that has the given text
     *
     * @param displayedText - case sensitive the text that the user sees
     * @return - the first element in the main nav bar that IS the text given in the param
     */
    public WebElement getNavItem(String displayedText) {
        /* Note the use of normalize() - it is most likely that the user's view of the link text is a normalized string,
         * but the IDE may have munged it (to no end effect - except for space chars sent)
         *
         * byLinkText consistently fails to find the these links, but the following is fast enough,and reliable
         */
        return myDriver
                .findElement(mainNav)
                .findElement(By.xpath("//a[normalize-space(text())='" + displayedText + "']"));
    }

    public int getNavItemCount() {
        return myDriver
                .findElement(mainNav)
                .findElements(navItem)
                .size();
    }
}
