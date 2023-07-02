package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import myUtility.ExtentTestManager;
import myUtility.MyUtility;
import myUtility.ScreenshotHelper;

public class hover extends MyUtility {
	WebDriver driver;
	ExtentTestManager testMgr = new ExtentTestManager();
	
	public hover(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public static final String productHover ="//button[contains(text(),'Products')]";
	@FindBy(xpath = productHover)
	WebElement hover;
	
	
	
	public void hoverfunction() {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		ExtentTest methodLogger = testMgr.setTestNode(methodName);
		
		try {
			Actions action = new Actions(driver);
			waitForElementPresentByXpath(productHover);
			action.moveToElement(hover).perform();
			
			methodLogger.info("Hovered on the element Produect");
			methodLogger.addScreenCaptureFromBase64String(ScreenshotHelper.getbase64Screenshot(),"Hovering");
			
		}
		catch(Exception e) {
			
		}
	}
}
