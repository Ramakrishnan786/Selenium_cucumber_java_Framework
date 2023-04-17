package myUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;







public class MyUtility {
	public static WebDriver driver;
	public static JavascriptExecutor js = (JavascriptExecutor) driver;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest logger;
	public static String ErrorMessage;
	public ThreadLocal<WebDriver> webDrivers = new ThreadLocal<WebDriver>();
	public Map<String,String> dataFromJson;
	Properties prop;
	public String  getValueFromPropertiesFile(String fileName, String key) throws Exception {
		String browserName = null;
		try {
		String projectpath = System.getProperty("user.dir");
		String propfilePath = projectpath +"/propertiesFile/"+fileName+".properties";
		BufferedReader reader = new BufferedReader(new FileReader(propfilePath));
		
		 prop = new Properties();
		prop.load(reader);
		 browserName = prop.getProperty(key);
		 
		System.out.println(browserName);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return browserName;
		
				
	}
	
	public void updationOfJsonFile(String fileName, String key, String updatedValue) {
		try {
			String projectpath = System.getProperty("user.dir");
			String propfilePath = projectpath +"/propertiesFile/"+fileName+".properties";
			BufferedReader reader = new BufferedReader(new FileReader(propfilePath));
			FileOutputStream fos = new FileOutputStream(new File(propfilePath));
			
			 prop = new Properties();
			prop.load(reader);
			prop.setProperty(key,updatedValue);
			prop.store(fos,updatedValue);
			
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
	}
//	public String getDriverFromPropertiesFile() {
//		
//		 String browserName = prop.getProperty("browser");
//		 
//		 if(browserName != null) 
//			return browserName;
//		 else
//			 throw new RuntimeException("Browser name not present in the properties file");
//	}
	
	public Map<String,String> getDataFromJsonFile(String jsonFileName, String jsonHeaderName) {
		Map<String, String> dataFromJson = null;
		try {
			
			JSONParser parser = new JSONParser();
			String projectPath = System.getProperty("user.dir");
			Object jsonObj = parser.parse(new FileReader(projectPath+"/JsonFiles/"+jsonFileName+".json"));
			JSONObject jso= (JSONObject) jsonObj;
			 dataFromJson = (Map<String, String>) jso.get(jsonHeaderName);
			 System.out.println(dataFromJson.toString());
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return dataFromJson;
	}
	//The below method is of version 3 but now upgraded to version 5.0
//	public void reportGenerator() {
//		try {
//			
//			ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter("testreport.html");
//			
//			ExtentReports ep = new ExtentReports();
//			ep.attachReporter(htmlreporter);
//			ExtentTest test = ep.createTest("First test");
//			
//			test.pass("First test case passed");
//			test.info("This is test info");
//			test.fail("This test is failed");
//			ep.flush();
//			
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
	
	public void excelFile() {
		try {
			XSSFWorkbook workbook  = new XSSFWorkbook("ExcelFiles/Book2.xlsx");
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int rowCount =sheet.getPhysicalNumberOfRows();
				System.out.println(rowCount);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public  WebDriver launchDriver() {
		try {
			String browserName = getValueFromPropertiesFile("browser","browser");
			String projectPath = System.getProperty("user.dir");
			if(browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",projectPath+"/drivers/chromedriver.exe");
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				
				Map<String,Object> chromePrefs = new HashMap<String, Object>();
				
				chromePrefs.put("profile.efault_content_settings.popups",0);
				chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting",1);
				chromePrefs.put("download.prompt_for_download",false);
				chromePrefs.put("safebrowsingg.enabled",true);
				options.setExperimentalOption("prefs",chromePrefs);
				
				options.addArguments("--remote-allow-origins=*");
				driver= new ChromeDriver(options);
				js = (JavascriptExecutor) driver;
				driver.manage().deleteAllCookies();
				System.out.println("Chrome launched successfully");
				//logger.info("Launched the chrome driver successfully");
				
			}
		}
		catch(Exception e) {
			ErrorMessage = e.toString();
			//logger.fail(ErrorMessage +" exception thrown under Catch Block");
		}
		return driver;
	}
	
	public void launchingOfTheURL() {
		try {
			
			String url = getValueFromPropertiesFile("url","url");
			
			driver.get(url);
			driver.manage().window().maximize();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ErrorMessage = e.toString();
			
			//logger.fail(ErrorMessage+ " exception thrown under Catch Block");
		}
		
	}
	
	public void closingBrowser() {
		driver.close();
		driver.quit();
	}
	
	public static boolean waitForElementPresentByXpath(String xpath) {
		Boolean result = false;
		try {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		result = true;
		}
		catch(Exception e) {
			ErrorMessage = e.toString();
			logger.fail("Error Message is "+ErrorMessage);
			
		}
		return result;
				
		
	}
	
}
