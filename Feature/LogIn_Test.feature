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
|TC2|