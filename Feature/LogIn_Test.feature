Feature: Login Action

Scenario Outline: Successful login to policy bazar site

Given User opens the browser
And opens the policy bazar site
When user fills all the details "<Testcase Name>"
And clicks on proceed button
When User enters the next page "<Testcase Name>"
And clicks on proceed button
When User searches search text on search bar
And clicks on search button
#When User clicks on View All Feature Details
#And gets the min and max premium amount

Examples:
|Testcase Name|
|TC1|

@RegressionTest
Scenario Outline: Successful login to MDDX site

Given User opens the browser
And opens the mddx site
When user fills the login details"<Testcase Name>"
And clicks on signin button and gets the last login detail
And User logout from application

Examples:
|Testcase Name|
|TC1|