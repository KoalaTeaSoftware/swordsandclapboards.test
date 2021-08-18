package testSuite.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/*
By default, Cucumber features/scenarios are run in the order:

1. Alphabetically by feature file directory
2. Alphabetically by feature file name
3. Order of scenarios within the feature file

 */

@RunWith(Cucumber.class)
@CucumberOptions(
        // features is vital, although IntelliJ doesn't think it is
        features = "src/test/java/testSuite/features", // all of the features in the test suite
        glue = { // a list of package names, not directly related to file paths
                "testFramework", // the @Before and @After will not be run unless the package containing them is listed here
                "testSuite"
        },
        plugin = {
                "pretty",
                "html:target/stdReports",
                "json:target/cucumber.json",
                // ToDo: replace this with a modern one
                // see https://gitlab.com/monochromata-de/cucumber-reporting-plugin
//                "de.monochromata.cucumber.report.PrettyReports:target/"
        }
//                , tags = "@smoke"
        //        , tags = "@standards"
        , tags = "@ci and (not (@not-ci or @wip))"
//                        ,dryRun = true
)

public class TestRunner {
}