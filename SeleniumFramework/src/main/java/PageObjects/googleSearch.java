package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import myUtility.ExtentTestManager;
import myUtility.MyUtility;

public class googleSearch  extends MyUtility{
	
	ExtentTestManager testManager = new ExtentTestManager();
	
	WebDriver driver = null;
	public googleSearch(WebDriver driver1) {
		this.driver= driver1;
		PageFactory.initElements(driver1,this);
		
	}
	public static final String searchLocator = "//textarea[@title='Search']";
	@FindBy(xpath = searchLocator)
	WebElement search;
	
	public static final String labelLocator = "//div[contains(text(),'Selenium automation')]";
	@FindBy(xpath = labelLocator)
	WebElement seleniumLabelLocator;
	
	
	public static final String icon = "//span[@class='landingSprite swipIcon']";
	@FindBy(xpath = icon)
	WebElement reverseIcon;
	
	public void search() {
		String methodName = new Throwable().getStackTrace()[0].getMethodName();
		ExtentTest methodLogger = testManager.setTestNode(methodName);
		
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//textarea[@title='Search']")).click();
			methodLogger.info("clicked on the search tab");
			driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys("Selenium Automation");
			methodLogger.info("Entered the text"+" Selenium Automation");
			//reverseIcon.click();
			search.click();
			search.sendKeys("Selenium Automation");
			methodLogger.info("again entered the input of Selenium Automation");
			search.sendKeys(Keys.RETURN);
			
			methodLogger.info("Clicked on the enter key");
//			WebDriverWait wait = new WebDriverWait(driver,20);
//			
//			wait.until(ExpectedConditions.visibilityOfElementLocated((By) seleniumLabelLocator));
			Thread.sleep(2000);
			//methodLogger.info(seleniumLabelLocator.getText());
			
			
		}
		catch(Exception e) {
			ErrorMessage = e.toString();
			logger.fail("Exception thrown "+ErrorMessage);
		}
	}
}
