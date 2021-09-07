package helpers.browsers;

import org.openqa.selenium.WebDriver;

public abstract class Browser {
    // My own local copy  of a web driver, it will be lazily created if it is ever wanted
    protected WebDriver myOwnDriver = null;

    /* ******************************************************************
     * Declare methods that the actual implementations of Actors must provide
     */
    protected abstract void startService();

    //  protected abstract void stopService(); // It makes sense to mention this, but it does not get used anywhere
    protected abstract WebDriver createDriver();

    /* ******************************************************************
     * Declare methods that the generic Actor offers the world
     */

    /**
     * The constructor, it forces the starting of the service that is relevant to this sort of Actor, no more
     */
    public Browser() {
        startService();
    }

    /**
     * Make my driver load the given resource
     *
     * @param fullURL - of the page that you want to see. It is best to use a fully qualified URL
     */
    public void getResource(String fullURL) {
        getDriver().get(fullURL);
    }

    /**
     * Expose the driver to the outside world, so that all of the interesting stuff that Selenium does is available
     * If necessary, it will make a driver
     *
     * @return - A working web driver
     */
    public WebDriver getDriver() {
        if (myOwnDriver == null) {
            System.out.println("[info] Lazy creation of driver.");
            myOwnDriver = createDriver();
        }
        return myOwnDriver;
    }

    /**
     * This is only intended for use by the framework to allow it to isolate each Scenario
     * It should not be used elsewhere, unless under extraordinary circumstances
     */
    public void closeDriver() {
        if (null != myOwnDriver) {
            System.out.println("[info] Closing driver");
            myOwnDriver.quit();
            myOwnDriver = null; // Essential to do this, else you keep getting the dead driver
        } else
            // maybe something has gone wrong with the framework?
            System.out.println("[info] No driver to close");
    }
}