package TestRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features ={"CucumberFeatures"} ,glue= {"StepDefinitions"},tags = "@hover" )
public class testRunner extends AbstractTestNGCucumberTests{

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios(){
		return super.scenarios();
		
	}
	
	
}
