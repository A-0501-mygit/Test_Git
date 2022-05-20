package testCases;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/test/resources/Feature/Test-scenarios-Group.feature"},
        glue={"src.test.java.testCases"}
)
class RunCucumberTest
{

}
//https://www.jetbrains.com/help/idea/creating-step-definition.html
