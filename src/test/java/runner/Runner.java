package runner;
import configuration.ConfigEnvironment;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue="runner",
        plugin = {"html:target/cucumber-html-report", "json:target/cucumber.json"}
)
public class Runner {
    //hooks
    @Before
    public void hookSetup() throws IOException {
        System.out.println("**** Iniciando........");
        new ConfigEnvironment().readValues();
    }

    @After
    public void hookCleanup(){
        System.out.println("**** Finalizando........");
    }


}
