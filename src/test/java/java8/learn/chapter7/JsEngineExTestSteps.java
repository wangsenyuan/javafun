package java8.learn.chapter7;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.script.ScriptException;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by senyuanwang on 15/5/21.
 */
public class JsEngineExTestSteps {

    JsEngineEx jsEngineEx = new JsEngineEx();
    public Object result = -1;

    @When("add (\\d+) and (\\d+)")
    public void whenAddTowNumbersInJs(int a, int b) throws ScriptException {
        result = Integer.parseInt(jsEngineEx.evalScript(a + " + " + b).toString());
    }

    @Then("the sum should be (\\d+)")
    public void testAddNumberResult(int result) {
        assertThat(result, equalTo(this.result));
    }

    @When("nashorn eval javascript file '(.+)'")
    public void whenEvalFile(String fileName) throws IOException, ScriptException {
        result = jsEngineEx.evalScriptFile(fileName);
    }

    @Then("eval javascript file will get (.+)")
    public void testScriptFileResult(Integer result) {
        assertThat(result, equalTo(this.result));
    }
}
