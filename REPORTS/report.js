$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Feature/LogIn_Test.feature");
formatter.feature({
  "line": 1,
  "name": "Login Action",
  "description": "",
  "id": "login-action",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Successful login to policy bazar site",
  "description": "",
  "id": "login-action;successful-login-to-policy-bazar-site",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "User opens the browser",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "opens the policy bazar site",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "user fills all the details",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "clicks on proceed button",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "User enters the next page",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "clicks on proceed button",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "User searches search text on search bar",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "clicks on search button",
  "keyword": "And "
});
formatter.match({
  "location": "TestSteps.user_opens_the_browser()"
});
formatter.result({
  "duration": 6159388149,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.opens_the_policy_bazar_site()"
});
formatter.result({
  "duration": 9462476076,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.user_fills_all_the_details()"
});
formatter.result({
  "duration": 13028690123,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.clicks_on_proceed_button()"
});
formatter.result({
  "duration": 1202340776,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.user_enters_the_next_page()"
});
formatter.result({
  "duration": 2491611195,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.clicks_on_proceed_button()"
});
formatter.result({
  "duration": 236916544,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.user_searches_search_text_on_search_bar()"
});
formatter.result({
  "duration": 20906,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.clicks_on_search_button()"
});
formatter.result({
  "duration": 11363214523,
  "status": "passed"
});
});