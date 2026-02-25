package org.runnerclass;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features="E:\\Selenium\\CLSSNervedemo\\src\\test\\resources\\nerve.feature\\nerve.feature",
snippets =SnippetType.CAMELCASE,
glue="org.steps"
)

public class RunnerClass {

}