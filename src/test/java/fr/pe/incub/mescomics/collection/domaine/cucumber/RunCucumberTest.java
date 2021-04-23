package fr.pe.incub.mescomics.collection.domaine.cucumber;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/generated-docs/cucumber-report.html" })
public class RunCucumberTest {

}