package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;



/**
 * Runner class to run as Cucumber test runner file.
 */

// Configurations for Cucumber Test Runner

@CucumberOptions(
        tags = "", // Define the tags to be executed (if any)
        features = {
                //Path of the featrure files
        },
        glue = {
                "StepDefinations" // Package where step definitions are located
        },
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty","html:test-output/CucumberReports/htmlReport.html", // Generates HTML report
                "pretty","json:test-output/CucumberReports/JsonReport.json"
        },
        monochrome = true, // Makes console output more readable
        publish = true

)

// Extends AbstractTestNGCucumberTests to run Cucumber tests with TestNG
public class CucumebrRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
