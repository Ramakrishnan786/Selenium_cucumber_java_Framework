package myUtility;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelperMethods {

	
	MyUtility utility = new MyUtility();
	
	
	
	public void getDriver() {
		try {
			String browser = utility.getValueFromPropertiesFile("prop","browser");
			System.out.println(browser);
			if(browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
