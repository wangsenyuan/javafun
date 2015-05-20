package java8.learn.chapter6;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by senyuanwang on 15/5/20.
 */
public class Exercise9Steps {

    Exercise9 exercise9 = new Exercise9();
    private int n;
    private int res;

    @When("cal the (\\d+)-th fib number")
    public void calFibNumAt(int n) {
        res = exercise9.fib(n);
    }

    @Then("will get fib number (\\d+)")
    public void testResult(int res) {
        assertThat(this.res, equalTo(res));
    }
}
