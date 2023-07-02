package PageObjects;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import myUtility.ExtentTestManager;
import myUtility.MyUtility;
import myUtility.ScreenshotHelper;

public class amazon extends MyUtility {
	WebDriver driver = null;
	ExtentTestManager test = new ExtentTestManager();
	public amazon (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public static final String cartCount = "//span[@id='nav-cart-count']";
	@FindBy(xpath = cartCount)
	WebElement countOfCart;
	
	public static final String searchInput = "//input[@id='twotabsearchtextbox']";
	@FindBy(xpath = searchInput)
	WebElement searchInputValue;
	
	public static final String searchInputButton = "//input[@id='nav-search-submit-button']";
	@FindBy(xpath = searchInputButton)
	WebElement searchInputValueButton;
	
	public static final String Redmiphone = "//div[@cel_widget_id='MAIN-SEARCH_RESULTS-2']//a";
	@FindBy(xpath = Redmiphone)
	WebElement RedmiPhone;
	
	public static final String costOfPhone = "(//span[contains(text(),'19,999')])[8]";
	@FindBy(xpath = costOfPhone)
	WebElement phoneCost;
	
	public static final String cartButton = "//input[@id='add-to-cart-button']";
	@FindBy(xpath = cartButton)
	WebElement cartBtn;
	
	public static final String addedToCartMessage ="//*[@id='attachDisplayAddBaseAlert']/span";
	@FindBy(xpath=addedToCartMessage)
	WebElement cartMessage;
	
	public void getThecartCount() {
		String methodName = new Throwable().getStackTrace()[1].getMethodName();
		ExtentTest methodLogger = test.setTestNode(methodName);
		
		try {
			String cartCount = countOfCart.getText();
			methodLogger.info("Cart count is "+ cartCount);
			methodLogger.addScreenCaptureFromBase64String(ScreenshotHelper.getbase64Screenshot(),"Cart Count");
			
		}
		catch(Exception e) {
			ErrorMessage = e.toString();
			logger.fail("Error Message "+ErrorMessage);
		}
	}
	
	public void addingCellPhoneinCart() {
		String methodName = new Throwable().getStackTrace()[1].getMethodName();
		ExtentTest methodLogger = test.setTestNode(methodName);
		try {
		dataFromJson= 	getDataFromJsonFile("amazon","amazon search Input cell phone");
			methodLogger.info("Input value in the search bar" + dataFromJson.get("0"));
			
			
			searchInputValue.click();
			methodLogger.info("Clicked on Search button");
			searchInputValue.sendKeys(dataFromJson.get("0"));
			methodLogger.addScreenCaptureFromBase64String(ScreenshotHelper.getbase64Screenshot(),"Input values in search bar");
			searchInputValueButton.click();
			waitForElementPresentByXpath(Redmiphone);
			if(RedmiPhone.isEnabled()) {
				
				RedmiPhone.click();
				methodLogger.info("Clicked on the redmi 15 phone");
				
			}
			
			Set<String> windowHandles = driver.getWindowHandles();
			System.out.println(windowHandles.toString());
			String windowhandle = null ;
			for(String window:windowHandles) {
				windowhandle = window;
			}
			driver.switchTo().window(windowhandle);
			methodLogger.info("Switched to the new tab");
			methodLogger.addScreenCaptureFromBase64String(ScreenshotHelper.getbase64Screenshot(),"New tabl Switching");
			
			waitForElementPresentByXpath(costOfPhone);
			
			cartBtn.click();
			
			methodLogger.info("Added to cart and item displayed");
			methodLogger.addScreenCaptureFromBase64String(ScreenshotHelper.getbase64Screenshot(),"Added to Cart");
			
			waitForElementPresentByXpath(addedToCartMessage);
			methodLogger.info(cartMessage.getText()+" is displayed");
			
			
		}
		catch(Exception e) {
			ErrorMessage = e.toString();
			logger.fail("Error Message "+ErrorMessage);
		}
	}
}
