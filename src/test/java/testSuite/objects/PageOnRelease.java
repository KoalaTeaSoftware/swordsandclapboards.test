package testSuite.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testFramework.objects.HtmlPage;

import java.util.List;

public class PageOnRelease {
    private final WebDriver myDriver;
    private final HtmlPage htmlPage;

    final private By locateFilmListItems = By.className("filmListItem");
    final private By locateFilmThumbnails = By.xpath("//*[contains(@class,'filmThumbnail')]");
    final private By locateFilmTitles = By.xpath(("//*[contains(@class,'textLinkToFilmDetails')]"));
    final private By locateFilmPuffs = By.className("filmPuff");

    public PageOnRelease(WebDriver webDriver) {
        myDriver = webDriver;
        htmlPage = new HtmlPage(myDriver); // use the wait inherent in this
    }

    public List<WebElement> getFilmListItems() {
        return myDriver.findElements(locateFilmListItems);
    }

    public List<WebElement> getFilmThumbnails() {
        return myDriver.findElements(locateFilmThumbnails);
    }

    public List<WebElement> getFilmTitles() {
        return myDriver.findElements(locateFilmTitles);
    }

    public List<WebElement> getFilmPuffs() {
        return myDriver.findElements(locateFilmPuffs);
    }
}
