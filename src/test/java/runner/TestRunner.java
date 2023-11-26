package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = {"stepDefinition"},
        plugin = {"pretty",
                "html:target/cucumber-reports.html", "json:target/cucumber.json"},
                //"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},

        monochrome = false,
        publish = true,
        tags = "@RegressionTest"
)
public class TestRunner {

}