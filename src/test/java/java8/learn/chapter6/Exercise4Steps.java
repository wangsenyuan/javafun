package java8.learn.chapter6;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by senyuanwang on 15/5/20.
 */
public class Exercise4Steps {
    private Exercise4 exercise = new Exercise4();
    private long max;
//    private long[] nums;

//    @Given("an array of integers (.+)")
//    public void setNums(long[] nums) {
//        this.nums = nums;
//    }

    @When("find the max from the following nums:")
    public void findMax(List<Long> nums) {
        long[] xs = new long[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            xs[i] = nums.get(i);
        }

        max = exercise.maxInArray(xs);
    }

    @Then("get the (\\d+) as the max")
    public void maxValueShouldBe(long result) {
        assertThat(max, equalTo(result));
    }
}
