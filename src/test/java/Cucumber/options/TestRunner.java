package Cucumber.options;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src\\test\\java\\features",
        glue={"stepDefination"},
        plugin="json:target/jsonReports/cucumber_report.json"
//        tags= "@DeletePlace"
        )
public class TestRunner {

}
