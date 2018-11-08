Feature: Amazon site testing
  This feature file will be used to test AMAZON site

  Background: User opens the browser
  Given User opens the browser

	@Login
  Scenario Outline: Amazon login
    Given User opens the web page "<Testcase Name>"
    And navigates to Sign in link and clicks on signin button
    And enters username and password "<Testcase Name>"
    And user verifies the logged in User "<Testcase Name>"
    When user clicks on home link
    And navigate back to previous page
    And clicks on Today's Deals
    When user searches item in search bar "<Testcase Name>"
    And open the item and does a zoom in of image displayed

    Examples: 
      | Testcase Name |
      | TC1           |
