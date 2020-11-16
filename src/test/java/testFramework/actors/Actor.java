package testFramework.actors;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import testFramework.Context;

public abstract class Actor {
    /**
     * Choose one of these when you instantiate your actor
     */
    public enum ActorType {
    }

    public Actor() {
        startService();
    }


    /**
     * Get the driver so you can do all of the interesting stuff that Selenium lets you do
     * If necessary, it will make a driver for you
     *
     * @return - A working web driver
     */
    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }

    /**
     * This is only intended for use by the framework to allow it to isolate each Scenario
     * It should not be used elsewhere, unless under extraordinary circumstances
     */
    public void closeDriver() {
        if (null != driver) {
            System.out.println("[info] Closing driver");
            driver.quit();
            driver = null; // Essential to do this, else you keep getting the dead driver
        } else
            // maybe something has gone wrong with the framework?
            System.out.println("[info] No driver to close");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Most of the different types of actor will have to do a different something at each of these points in the in the test lifecycle
    // So force them to be created

    /**
     * @param fullURL - of the page that you want to see. Can be relative
     */
    public void getResource(String fullURL) {
        getDriver().get(fullURL);
    }

    /**
     * Just what is says on the tin.
     *
     * @param message - what you ant to see in the report
     */
    public static void writeToHtmlReport(String message) {
        Context.scenario.write(message);
    }

    /**
     * Defined within the actor because the different types of actor behave in different ways
     * This definition is good for the various browsers and has to be overridden for the API and Appium actors
     *
     * @param message - what you want to se written in the report
     */
    public void writeScreenShotToHtmlReport(String message) {
        TakesScreenshot ts = (TakesScreenshot) Context.defaultDriver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

        Context.scenario.embed(screenshot, "image/png", message);

        /* for IE
        BufferedImage image = new Robot().createScreenCapture(new    Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
ImageIO.write(image, "png", new File("/screenshot.png"));
         */
    }

    public void writePageSourceToHtmlReport() {
        writeToHtmlReport(Context.defaultDriver.getPageSource());
    }

    protected abstract void startService();

    // --Commented out by Inspection START (11/10/2020 20:28):
    //    /**
    //     * This may never actually be called. The service may just fade away as the test dies
    //     */
    //    protected abstract void stopService();
    // --Commented out by Inspection STOP (11/10/2020 20:28)

    protected abstract void createDriver();

    protected WebDriver driver;

}
