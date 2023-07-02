package PageObjects;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import myUtility.HelperMethods;

public class LoginChecks {
	
	
	HelperMethods method = new HelperMethods();
	
	public void login () {
		try {
			WebDriver driver = null;
			WebDriverManager.chromedriver().setup();
			driver.get("https://practicetestautomation.com/practice-test-login/");
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
