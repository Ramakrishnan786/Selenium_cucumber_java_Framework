package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import myUtility.ExtentManager;
import myUtility.MyUtility;
import myUtility.ScreenshotHelper;
import myUtility.WebDriverManager;

public class Hooks extends MyUtility{
	Scenario scenario = null;
	ExtentManager extMgr = new ExtentManager();
	
	@Before
	public void beforeScenrio(Scenario scenario) {
		
		this.scenario = scenario;
		extMgr.generateReport(scenario);
		
	}
	
//	@Before
//	public void setUpBrowser() {
//		WebDriverManager driverManager = new WebDriverManager();
//		webDrivers.set(driverManager.getDriver());
//	}
	@Before
	@Given("Launching the browser mentioned in the config file")
	public void launching_the_browser_mentioned_in_the_config_file() {
	    // Write code here that turns the phrase above into concrete actions
	    launchDriver();
	}
	
	@After
	public void afterEachScenario() {
		if(scenario.isFailed()) {
			System.out.println("The scenario is failed");
			try {
				logger.addScreenCaptureFromBase64String(ScreenshotHelper.getbase64Screenshot(),scenario.getName());
			}
			catch(Exception e) {
				ErrorMessage = e.toString();
				logger.fail("Error Message is " +ErrorMessage);
			}
		}
	}
	
	@After
	@When("User logs out or closing the driver")
	public void user_logs_out_or_closing_the_driver() {
	    // Write code here that turns the phrase above into concrete actions
		MyUtility.extent.flush();
		
		closingBrowser();
	}
}
