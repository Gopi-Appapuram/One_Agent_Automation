package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "", // Define the tags to be executed (if any)
        features = {
                //Path of the featrure files
        },
        glue = {
                "StepDefinations" // Package where step definitions are located
        },
        monochrome = true, // Makes console output more readable
        publish = true,
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "pretty","html:test-output/CucumberReports/htmlReport.html", // Generates HTML report
                "pretty","json:test-output/CucumberReports/JsonReport.json"
        }
)
public class JunitRunner {
}
