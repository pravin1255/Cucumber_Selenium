package com.policy.cucumberTest;

import java.io.IOException;


import org.junit.BeforeClass;
import org.junit.runner.RunWith;



import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
		features="Feature/LogIn_Test.feature",
		glue={"com.policy.stepDefinition"},
		format={"json:REPORTS/cucumber.json", "pretty"},		
		monochrome=true,
		tags={"@RegressionTest"}
		)
public class TestRunner {

	
}


