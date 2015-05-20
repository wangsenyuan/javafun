package cucumber.learn.book;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * Created by senyuanwang on 15/5/20.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class BookSearchTest {
}
