package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // features is vital, although IntelliJ doesn't think it is
        features = "src/test/features", // a file path to the root of all features relevant to this runner
        glue = {
                // a list of package names, not directly related to file paths
                // the @Before and @After will not be run unless the package containing them is listed here
                "steps.frame",
                "steps.sut"
        },
        plugin = {
//                "pretty",
//                "html:target/htmlReport",
//                "json:target/cucumber.json",
                "me.jvt.cucumber.report.PrettyReports:target"
        }
        , tags = "not (@wip or @pot)"
//        , tags = "(not @wip) and (not @pot) and @smoke"
//        , tags = "(not @wip) and @pot"
//        , dryRun = true
)

public class FrameworkRunner {
}