package StepDefinitions;

import org.openqa.selenium.WebDriver;

import PageObjects.amazon;
import PageObjects.googleSearch;
import PageObjects.urlLauching;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import myUtility.MyUtility;

public class stefDef {
	
	WebDriver driver = MyUtility.driver;
	urlLauching url = new urlLauching(driver);
	
	
	
	
	googleSearch search = new googleSearch(driver);
	amazon obj1 = new amazon(driver);

//	@Given("Navigating to the (.*) specified in the url config file")
//	public void navigating_to_the_specified_in_the_url_config_file(String urlName) {
//	    // Write code here that turns the phrase above into concrete actions
//		
//	}
	
//	@Given("Navigating to the (.*) specified in the url config file")
//	public void navigating_to_the_urlName_specified_in_the_url_config_file(String urlName) {
//	    // Write code here that turns the phrase above into concrete actions
//		url.launching_of_the_url(urlName);
//	}
	
	@Given("^Navigating to the (.*) specified in the url config file$")
	public void navigating_to_the_urlName_specified_in_the_url_config_file(String urlName) {
	    // code to navigate to the website URL
		url.launching_of_the_url(urlName);
	}
	

//	@Given("Navigating to the google specified in the url config file")
//	public void navigating_to_the_google_specified_in_the_url_config_file() throws Exception{
//    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
//	}
	
	@When("User searches on the google")
	public void User_searches_on_the_google() {
	    // Write code here that turns the phrase above into concrete actions
		search.search();
	}
	
	@When("Getting the Count of the Cart")
	public void getting_the_count_of_the_cart() {
	    // Write code here that turns the phrase above into concrete actions
	   obj1.getThecartCount();
	}

	@When("Adding a cellphone in the cart")
	public void adding_a_cellphone_in_the_cart() {
	    obj1.addingCellPhoneinCart();
	    
	}

	@Then("Checking the added item in the cart")
	public void checking_the_added_item_in_the_cart() {
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	
	
	
	

}
