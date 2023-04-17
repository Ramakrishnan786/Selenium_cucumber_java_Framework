package myUtility;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotHelper extends MyUtility {
	
	public static String getbase64Screenshot() {
		String img ="";
		File source, destination = null;
		
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			source = ts.getScreenshotAs(OutputType.FILE);
			destination = new File (System.getProperty("user.dir")+"/target/screenshots/"+timestamp+".png");
			FileUtils.copyFile(source, destination);
			byte[] byteimg = IOUtils.toByteArray(new FileInputStream(destination));
			img = Base64.getEncoder().encodeToString(byteimg);
		}
		catch(Exception e) {
			ErrorMessage = e.toString();
			logger.fail("Error message "+ErrorMessage);
		}
		return img;
	}
}
