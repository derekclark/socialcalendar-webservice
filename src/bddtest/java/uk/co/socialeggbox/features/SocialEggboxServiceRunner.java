package uk.co.socialeggbox.features;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/bddtest/resources")
public class SocialEggboxServiceRunner {
}
