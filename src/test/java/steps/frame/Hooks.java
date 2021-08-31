package steps.frame;

import helpers.Reports;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import objects.frame.Context;

public class Hooks {
    @Before
    public void beforeScenario(Scenario givenScenario) {
        System.out.println("[info] Scenario " + givenScenario.getName() + " starting");
        Context.currentScenario = givenScenario;
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("[info] Scenario " + Context.currentScenario.getName() + " completed");

        // this may seem a bit involved, but a direct use of getStatus().equals does not yield the hoped-for result
        if (scenario.isFailed()) {
            Reports.writeScreenShotToHtmlReport("Screenshot taken because this scenario is marked as " + scenario.getStatus().toString());
            Reports.writePageSourceToHtmlReport();
        }

        // ensure that the next scenario starts with a clean slate
        Context.defaultActor.closeDriver();
    }

}
