Feature: Login Action 
Background: Opening the browser 
	Given User opens the browser
@Policy 
Scenario Outline: Verify the amount displayed in Sum insured is same in both pages 
	And opens the policy bazar site 
	When user fills all the details "<Testcase Name>" 
	And selects two policy 
	And clicks on Compare now button 
	Then verify the amount displayed in Sum insured is same in both pages "<Testcase Name>" 
	Examples: 
		| Testcase Name |
		| TC1           |
		
@ComparePolicy 
Scenario Outline: Successful login to policy bazar site 
	And opens the policy bazar site 
	When user fills all the details "<Testcase Name>" 
	And selects two policy 
	And clicks on Compare now button 
	Then Gets the monthly amount of both the policy with difference 
	Examples: 
		| Testcase Name |
		| TC1           |
		
@AddPolicy 
Scenario Outline: Verifying a new policy can be added 
	And opens the policy bazar site 
	When user fills all the details "<Testcase Name>" 
	And selects two policy 
	And clicks on Compare now button 
	Then User adds a new policy 
	Examples: 
		| Testcase Name |
		| TC1           |

@changePolicyAmt 
Scenario Outline: Verifying a new cover can be selected 
	And opens the policy bazar site 
	When user fills all the details "<Testcase Name>" 
	And changes the policy cover to another amount "<Testcase Name>"
	And clicks on apply button 
	Examples: 
		| Testcase Name |
		| TC4           |
				
@RegressionTest 
Scenario Outline: Successful login to MDDX site 
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
And Logout from Gmail Account 
When "Approver 1" logins in 
When User Enters Username and password of "Approver 1" 
And clicks on sign in button 
When "Approver 1" opens the mail received by of subjectLines "<Testcase Name>" 
And "Approver 1" confirms the Subject line and mail body "<Testcase Name>" 
And send to "Approver 2" 
And Logout from Gmail Account 
When "Approver 2" logins in 
When User Enters Username and password of "Approver 2" 
And clicks on sign in button 
When "Approver 2" opens the mail received by of subjectLines "<Testcase Name>" 
And "Approver 2" confirms the Subject line and mail body "<Testcase Name>" 

#And clicks on reply button
#And sends response to Requester
#When Requester logins in
#When User Enters Username and password "<Testcase Name>"
#And clicks on sign in button
#And opens the mail received by Approver2
Examples: 
	| Users     | Testcase Name | Level |
	| Requester | TC1           | WK1   |
				
@WishList 
Scenario Outline: add item to wishlist 
Given User opens the browser 
And opens flipkart site 
When user login to site "<Testcase Name>" 
And search for product "<Testcase Name>" 
And clicks on first product "<Testcase Name>" 
When browser switch to new tab 
And User verifies the product and click on wishlist button "<Testcase Name>" 
And User verifies the message when they click on wishlist button 
And user closes the window and switch to Parent tab 
When user clicks on wishlist link 
And verifies whether added item is in wishlist"<Testcase Name>" 
And Users removes the item from wishlist "<Testcase Name>" 

Examples: 
	| Testcase Name |
	| TC2           |
					
@FileUpload 
Scenario Outline: File upload functionality testing 
Given User opens the browser 
And go to gmail login page 
When User Enters Username and password of "Requester" 
And clicks on sign in button 
When users clicks on Compose button 
And Clicks on attach button 
And attaches the file for uploading 
Then the files gets uploaded and Users checks the attached file 

Examples: 
	| Testcase Name |
	| Requester     |
						
@FileDownload3Users 
Scenario Outline: 
File upload,Download and workflow functionality testing 
Given User opens the browser 
And go to gmail login page 
When Users implements the given workflow "<Workflow Name>" 
And Loggins with the users 
And clicks on sign in button 
When users clicks on Compose button 
And Clicks on attach button 
And attaches the file for uploading "<Testcase Name>" 
Then the files gets uploaded and Users checks the attached file 
And users enters subject line "<Testcase Name>" and sends the mail to "APPROVER 1" 
And Loggins with the users 
And clicks on sign in button 
When user opens the received mail from "<Testcase Name>" 
And clicks on download link 

Examples: 
	| Testcase Name | Workflow Name |
	| TC2           | WK2           |
							
@FileUploadValidation 
Scenario Outline: 
File upload,Download and than verifying some fields from downloaded file 
Given User opens the browser 
And go to gmail login page 
When Users implements the given workflow "<Workflow Name>" 
And Loggins with the users 
And clicks on sign in button 
When users clicks on Compose button 
And Clicks on attach button 
And attaches the file for uploading "<Testcase Name>" 
Then the files gets uploaded and Users checks the attached file 
And users enters subject line "<Testcase Name>" and sends the mail to "APPROVER 1" 
And Loggins with the users 
And clicks on sign in button 
When user opens the received mail from "<Testcase Name>" 
And clicks on download link 
When user opens the downloaded file 
And checks the fields whether its having the required text "<Testcase Name>" 
Then take the password from text field and paste in excel sheet "<Testcase Name>" 

Examples: 
	| Testcase Name | Workflow Name |
	| TC3           | WK3           |
