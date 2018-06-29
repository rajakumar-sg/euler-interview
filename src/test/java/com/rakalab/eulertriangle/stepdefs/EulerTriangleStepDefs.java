package com.rakalab.eulertriangle.stepdefs;

import com.rakalab.eulertriangle.Builder;
import com.rakalab.eulertriangle.EulerTriangle;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EulerTriangleStepDefs {

    EulerTriangle eulerTriangle;
    String inputData;

    @Before
    public void setup() {
        eulerTriangle = null;
        inputData = null;
    }

    @Given("^Input data as below$")
    public void inputDataAs(List<String> input) {
        inputData = input.stream().reduce((a, b) -> a + "\n" + b).get();
    }

    @When("^build triangle with input data$")
    public void buildTriangleWithInputData() throws Throwable {
        Builder builder = new Builder();
        eulerTriangle = builder.buildTriangle(inputData);
    }

    @Then("^Maximum sum of the triangle should be (\\d+)$")
    public void maximumSumOfTheTriangleShouldBe(int maxSum) throws Throwable {
        assertThat(eulerTriangle.getMaximumSum(), is(maxSum));
    }

    @And("^Highest path is \"([^\"]*)\"$")
    public void highestPathIs(String path) throws Throwable {
        assertThat(eulerTriangle.getHighestSumPath(), is(path));
    }
}
