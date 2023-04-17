package myUtility;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager extends ExtentManager {
	ExtentTest test;
	
	public ExtentTest setCurrentTest(String testName) {
		test = extent.createTest(testName);
		return test;
	}
	
	public ExtentTest setTestNode(String testNode) {
		test = logger.createNode(testNode);
		return test;
	}
	
	public ExtentTest setCurrentTestNode(ExtentTest testName, String testNode) {
		test = testName.createNode(testNode);
		return test;
	}
}
