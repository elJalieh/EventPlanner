package special.planner.AcceptanceTest;

import io.cucumber.java.en.*;

public class login {
    @Given("the user accesses the sign-in command")
    public void theUserAccessesTheSignInCommand() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("the user provides valid information {string} , {string}, {string}")
    public void theUserProvidesValidInformation(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the user should be successfully logged in")
    public void theUserShouldBeSuccessfullyLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
    }

    //////////////////////////////////////////////////////////
    @Given("the user accesses the login command")
    public void theUserAccessesTheLoginCommand() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("the user provides valid information {string} , {string} and something went wrong")
    public void theUserProvidesValidInformationAndSomethingWentWrong(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the system should display an error message {string}")
    public void theSystemShouldDisplayAnErrorMessage(String string) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the user should not be logged in")
    public void theUserShouldNotBeLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
    }

///////////////////////////////
    @When("the user provides information {string} , {string}")
    public void theUserProvidesInformation(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }
}
