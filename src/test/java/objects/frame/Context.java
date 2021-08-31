package objects.frame;

import helpers.ConfigReader;
import helpers.actors.Actor;
import helpers.actors.ActorFactory;
import helpers.actors.ActorType;
import io.cucumber.java.Scenario;
import org.junit.Assert;

import java.io.IOException;

/**
 * The context is set up at the beginning of the run, by reading configuration files
 * The code is intended to be fairly robust, providing defaults for a few data, should they be absent form the config files
 * Some data is refreshed when each new scenario starts
 */
public class Context {
    /*
     * The context is a classic singleton
     */
    private static final Context me = new Context();

    final String initialConfigFileLocation = "src/test/config/testFramework.properties";

    public static helpers.ConfigReader testConfiguration;
    public static helpers.ConfigReader sutConfiguration;

    public static Integer implicitWait;
    public static Integer pageLoadWait;

    public static Scenario currentScenario; // This is refreshed by the relevant hook
    public static ActorType defaultActorType;
    public static Actor defaultActor; // This is set up when the run starts

    private Context() {
        System.out.println("[info] Setting up the context");
        try {
            testConfiguration = new ConfigReader(initialConfigFileLocation);
            sutConfiguration = new ConfigReader(testConfiguration.getProperty("sutConfigPath"));
        } catch (IOException | NoSuchFieldException e) {
            e.printStackTrace();
            Assert.fail("Problem loading configuration for the test");
        }

        try {
            System.setProperty("cucumber.reporting.java.config.file", testConfiguration.getProperty("reportConfigFile"));
        } catch (NoSuchFieldException e) {
           Assert.fail("The report configuration file location must be defined the the test configuration");
        }

        try {
            implicitWait = Integer.parseInt(Context.testConfiguration.getProperty("implicitWaitTime"));
        } catch (NoSuchFieldException e) {
            implicitWait = 0;
        } catch (NumberFormatException e) {
            Assert.fail("Unable to understand the implicitWaitTime test configuration value");
        }

        try {
            pageLoadWait = Integer.valueOf(Context.testConfiguration.getProperty("waitForPageLoad"));
        } catch (NoSuchFieldException e) {
            System.out.println("[info] Defaulting Page load wait to zero");
            pageLoadWait = 0;
        } catch (NumberFormatException e) {
            Assert.fail("Unable to understand the waitForPageLoad test configuration value");
        }

        try {
            defaultActorType = ActorType.valueOf(testConfiguration.getProperty("defaultBrowser").toUpperCase());
        } catch (NoSuchFieldException e) {
            defaultActorType = null;
        }

        // I can not specifically say here that there must be a default actor.
        //noinspection ConstantConditions
        defaultActor = ActorFactory.make(defaultActorType);
    }
}
