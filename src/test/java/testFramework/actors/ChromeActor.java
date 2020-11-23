package testFramework.actors;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import testFramework.Context;

import java.util.concurrent.TimeUnit;

public class ChromeActor extends Actor {

    /*
    If you leave the page load strategy NORMAL, then you can get lots of errors like this
    [1584601749.116][SEVERE]: Timed out receiving message from renderer: 0.100
    (which aren't actually severe)
    If you set it to NONE (as in the following line),
    1) You don't get the errors
    2) the page's secondary stuff does not load before Selenium lets you go on
       so you will want to implement your own 'wait for load to complete' on the DriverManager's getPage
    */
    private final PageLoadStrategy pls = PageLoadStrategy.NONE;
    private ChromeDriverService service;

    @Override
    protected void startService() {
        WebDriverManager.chromedriver().setup();
        //        if (null == service) {
        //            System.out.println("[info] Creating a Driver Service for Chrome");
        //            // i.e. Lazy Instantiation of the Service
        //            try {
        //                String path = Context.testConfiguration.getProperty("chromeDriverPath");
        //                //noinspection SpellCheckingInspection - webdriver is good
        //                System.setProperty("webdriver.chrome.driver", path);
        //                service = new ChromeDriverService.Builder()
        //                        .usingDriverExecutable(new File(path))
        //                        .usingAnyFreePort()
        //                        .build();
        //                service.start();
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //                Assert.fail();
        //            }
        //        }
    }

    //    // NB: this is unused at the moment, but is here for completeness
    //    @Override
    //    protected void stopService() {
    //        if (null != service && service.isRunning()) {
    //            System.out.println("[info] Stopping the Driver Service for Chrome");
    //            service.stop();
    //        }
    //    }

    @Override
    public void createDriver() {
        System.out.println("[info] Creating a Web Driver for Chrome");
        ChromeOptions options = new ChromeOptions();
        // ChromeDriver is just AWFUL because every version or two it breaks unless you pass cryptic arguments
        try {
            if (Context.testConfiguration.getProperty("windowMaximize").equalsIgnoreCase("true"))
                options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        } catch (NoSuchFieldException e) {
            // you are probably better off setting it to be maximised
        }

        try {
            String windowSize = Context.testConfiguration.getProperty("windowSize");
            if (windowSize.length() > 0)
                if (windowSize.toLowerCase().contains("max"))
                    options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                else
                    options.addArguments("--window-size=" + Context.testConfiguration.getProperty("windowSize"));

        } catch (NoSuchFieldException e) {
            // do nothing if this property has not been defined
        }

        try {
            if (Context.testConfiguration.getProperty("headless").equalsIgnoreCase("true"))
                options.addArguments("--headless"); // only if you are ACTUALLY running headless
        } catch (NoSuchFieldException e) {
            // do nothing if this property has not been defined
        }
        options.setPageLoadStrategy(pls);
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        // no sandbox tends to cure the random 'can't find open windows' failure
        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        //                options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
        //                options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        //                options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        //                options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
        //                options.addArguments("enable-features=NetworkServiceInProcess");

        driver = new ChromeDriver(options);

        // todo - this causes it to just hang, so find out why and replace it with a correct code to set the implicit waits
        driver.manage().timeouts().implicitlyWait(Context.implicitWait, TimeUnit.SECONDS);
    }
}
