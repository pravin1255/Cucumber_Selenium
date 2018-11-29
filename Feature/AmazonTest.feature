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
    #When user clicks on home link
    #And navigate back to previous page
    #And clicks on Today's Deals
    When user searches item in search bar "<Testcase Name>"
    And open the item and does a zoom in of image displayed
    And user clicks on Buy Now button
    And user edits the mailing address "<Testcase Name>"

    Examples: 
      | Testcase Name |
      | TC1           |

  @AddtoCart
  Scenario Outline: Add to cart testing
    Given User opens the web page "<Testcase Name>"
    And navigates to Sign in link and clicks on signin button
    And enters username and password "<Testcase Name>"
    And user verifies the logged in User "<Testcase Name>"
    When user searches item in search bar "<Testcase Name>"
    And open the item and does a zoom in of image displayed
    And user first verifies the cart image
    And then clicks on Add to cart and again verfies the cart image
    And goes to cart page

    Examples: 
      | Testcase Name |
      | TC2           |

  @ShoesPage
  Scenario Outline: Navigating to Men's shoes page
    Given User opens the web page "<Testcase Name>"
    And navigates to Sign in link and clicks on signin button
    And enters username and password "<Testcase Name>"
    And user verifies the logged in User "<Testcase Name>"
    And User navigates to Men's Sport shoes section

    Examples: 
      | Testcase Name |
      | TC3           |

  @MoveToTop
  Scenario Outline: Moving item to top of wishlist
    Given User opens the web page "<Testcase Name>"
    And navigates to Sign in link and clicks on signin button
    And enters username and password "<Testcase Name>"
    And user verifies the logged in User "<Testcase Name>"
    And user moves to Wishlist page
    And moves item to top of wishlist "<Testcase Name>"
    Then item should be displayed at top of wishlist

    Examples: 
      | Testcase Name |
      | TC4           |

    @BuyNow
    Scenario Outline:Buying product from amazon
    Given User opens the web page "<Testcase Name>"
    And navigates to Sign in link and clicks on signin button
    And enters username and password "<Testcase Name>"
    Then user verifies the logged in User "<Testcase Name>"
    And user moves to Wishlist page
    And searches for the item and clicks on item "<Testcase Name>"
    Then User navigates to the product page
    When user selects the size "<Testcase Name>"
    And clicks on Buy Now button
    Then User is taken to delivery page
    
    Examples:
    |Testcase Name|
    |TC5|
    