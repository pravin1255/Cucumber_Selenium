package com.policy.cucumberTest;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="Feature/LogIn_Test.feature",
		glue={"com.policy.stepDefinition"}
		)
public class TestRunner {

}
