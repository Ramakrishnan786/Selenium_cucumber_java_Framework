#Author: your.email@your.domain.com

@Sanity
Feature: Hover Function
  Hovering and clicking on the element

  @hover
  Scenario Outline: Hover and navigate to the required path
 		Given Navigating to the <urlname> specified in the url config file
  	And check the hover funtionality
    When validation once reached to the website
   
   	Examples:
  		|urlname|
  		|BrowserStack|
   
   