#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@sanity
Feature: amazon cart validations
  Amazon file validations

  @amazon
  Scenario Outline: Checking and adding the items in the cart
    Given Navigating to the <urlname> specified in the url config file
    When Getting the Count of the Cart
    When Adding a cellphone in the cart
    Then Checking the added item in the cart
    
    Examples:
    |urlname|
    |amazon|