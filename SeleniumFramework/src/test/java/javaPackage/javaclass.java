package javaPackage;

import org.openqa.selenium.By;

import PageObjects.LoginChecks;
import myUtility.HelperMethods;
import myUtility.MyUtility;


public class javaclass extends MyUtility{
	
	static By locator = By.id("name");
	
//	public  String getDriver() {
//		String a = getDriverFromPropertiesFile();
//		return a;
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javaclass obj = new javaclass();
		MyUtility obj1 = new MyUtility();
		HelperMethods method = new HelperMethods();
		LoginChecks check = new LoginChecks();
		
		//WebDriver driver;
//		WebDriverManager.chromedriver().setup(); 
//		WebDriverManager.edgedriver().setup();
//		WebDriver driver = new EdgeDriver() ;
		//String path = System.getProperty("user.dir");
		
//		System.setProperty("webdriver.chrome.driver",path+ 
//				"/driver/geckodriver.exe");
		
		//driver.get("https://www.google.co.in/");
		try {
			//driver.findElement(locator);
			
//			obj1.updationOfJsonFile("prop","browser2","Rama1");
//			obj1.getValueFromPropertiesFile("prop","browser2");
//			obj1.getDataFromJsonFile("json","Names");
			//obj1.reportGenerator();
			obj1.excelFile();
			check.login();
//			String b = obj.getDriver();
//			System.out.println(b);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		driver.close();
//		driver.findElement(By.id("")).sendKeys(Keys.RETURN);
//		driver.quit();
	}

}
