package testFramework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // features is vital, although IntelliJ doesn't think it is
        features = "src/test/java/testFramework/features", // a file path to the root of all features relevant to this runner
        glue = {
                // a list of package names, not directly related to file paths
                // the @Before and @After will not be run unless the package containing them is listed here
                "testFramework"
        },
        plugin = {
                "pretty",
                "html:target/stdReports.html",
                "json:target/cucumber.json",
                // ToDo: replace this with a different reporting mechanism
                // see https://gitlab.com/monochromata-de/cucumber-reporting-plugin
//                "de.monochromata.cucumber.report.PrettyReports:target/"
        }
        , tags = "@smoke"
//                , dryRun = true
)

public class FrameworkRunner {
}