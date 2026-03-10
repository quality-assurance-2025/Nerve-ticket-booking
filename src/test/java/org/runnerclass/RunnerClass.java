package org.runnerclass;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.report.Report;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
features="E:\\Selenium\\CLSSNervedemo\\src\\test\\resources\\nerve.feature\\nerve.feature",
snippets = SnippetType.CAMELCASE,
plugin = {"pretty",
		"html:target/cucumber-report.html",
		"json:target/cucumber.json"}
)

public class RunnerClass {

    @AfterClass
    public static void report() {
        Report.generateReport();
    }

}