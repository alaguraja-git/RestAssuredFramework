
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features/"},
        glue = {"steps","TestInitialize"},
        tags = "@usingLOMBOK",
        plugin = {"pretty",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        },
        monochrome=true
)
public class TestRunner {


}
