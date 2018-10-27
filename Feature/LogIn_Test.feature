Feature: Login Action

  @Policy
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
      | Testcase Name |
      | TC1           |
      | TC2           |

  @RegressionTest
  Scenario Outline: Successful login to MDDX site
    Given User opens the browser
    And opens the mddx site
    When user fills the login details"<Testcase Name>"
    And clicks on signin button and gets the last login detail
    And User logout from application

    Examples: 
      | Testcase Name |
      | TC1           |

  @flipkartTest
  Scenario Outline: search an item
    Given User opens the browser
    And opens flipkart site
    When user login to site "<Testcase Name>"
    And search for iphone "<Testcase Name>"
    And gets the first item amount

    Examples: 
      | Testcase Name |
      | TC1           |

  @BackgroundColor
  Scenario Outline: search an item
    Given User opens the browser
    And opens flipkart site
    When user login to site "<Testcase Name>"
    And search for product "<Testcase Name>"
    And clicks on first product
    When browser switch to new tab
    And user enters the pin code

    Examples: 
      | Testcase Name |
      | TC1           |

  @Gmailapproval
  Scenario Outline: 2 level of approval
    Given Read the Workflow Levels"<Level>"
    Given User opens the browser
    And go to gmail login page
    When User Enters Username and password of "Requester"
    And clicks on sign in button
    When users clicks on Compose button
    And composes mail "<Testcase Name>" and sends to "Approver 1"
   # And Logout from Gmail Account
    #When "Approver_1" logins in
    #When User Enters Username and password of "Approver 1"
    #And clicks on sign in button
    #When "Approver 1" opens the mail received by "Requester"
    #And confirms the Subject line and mail body
    #And clicks on Approved button
    #And send to Approver2
    #When Approver 1 logins in
    #When User Enters Username and password "<Testcase Name>"
    #And clicks on sign in button
    #When Approver 2 opens the mail received by Approver1
    #And confirms the Subject line and mail body
    #And clicks on reply button
    #And sends response to Requester
    #When Requester logins in
    #When User Enters Username and password "<Testcase Name>"
    #And clicks on sign in button
    #And opens the mail received by Approver2
    Examples: 
      | Users      | Testcase Name | Level |
      | Requester  | TC1           | WK1   |
