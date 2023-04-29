package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import myUtility.ExtentTestManager;
import myUtility.MyUtility;
import myUtility.ScreenshotHelper;

public class urlLauching extends MyUtility{
	WebDriver driver = null;
	ExtentTestManager testMgr = new ExtentTestManager();
	public urlLauching(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void  launching_of_the_url(String urlName) {
		String url;
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		ExtentTest methodLogger = testMgr.setTestNode(methodName);
		try {
			url = getValueFromPropertiesFile("url",urlName);
			
			driver.get(url);
			methodLogger.info("Naviagted to url " + url);
			driver.manage().window().maximize();
			methodLogger.info("Window maximized");
			methodLogger.addScreenCaptureFromBase64String(ScreenshotHelper.getbase64Screenshot(),"Google");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
