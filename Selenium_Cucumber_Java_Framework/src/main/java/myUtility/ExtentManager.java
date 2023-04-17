package myUtility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.Scenario;

public class ExtentManager extends MyUtility {
	ExtentSparkReporter sparkReporter;
	public static String path;
	
	public void generateReport(Scenario scenario) {
		try {
			String currentDate = new SimpleDateFormat("dd-MMM-yy").format(new Date()).toString();
			String reportName = scenario.getSourceTagNames().toArray()[1].toString().substring(1);
			System.out.println(scenario.getSourceTagNames());
			System.out.println(scenario.getName());
			 
			path = System.getProperty("user.dir")+"/target/Reports/"+"report"+currentDate+".html";
			sparkReporter = new ExtentSparkReporter(path);
			
			sparkReporter.loadXMLConfig("extent-config.xml");
			 sparkReporter.config().thumbnailForBase64();
			sparkReporter.config().enableOfflineMode(false);
			
			logger = extent.createTest(scenario.getName());
			extent.attachReporter(sparkReporter);
			
		}
		catch(Exception e) {
			
		}
	}
}
