package special.planner;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class loginStepDefinition {

    private final Login login;

    public loginStepDefinition(Login login){
        this.login = login;
    }
    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
        login.initializeUsers();
        assertFalse(login.logInStatus);

    }
    @When("the information is valid email is {string} and password is {string}")
    public void theInformationIsValidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions

        for (User i : login.users) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                login.logInStatus = true;
                break;
            }
        }
        assertTrue(login.logInStatus);

    }
    @Then("user successfully log in")
    public void userSuccessfullyLogIn() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("the email is invalid email is {string} and password is {string}")
    public void theEmailIsInvalidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        if(!login.isValid(email, password)){
            login.logInStatus = false;
        }
        assertFalse(login.logInStatus);


    }
    @Then("user failed in log in")
    public void userFailedInLogIn() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("the password is invalid email is {string} and password is {string}")
    public void thePasswordIsInvalidEmailIsAndPasswordIs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("the information are invalid email is {string} and password is {string}")
    public void theInformationAreInvalidEmailIsAndPasswordIs(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions

    }
}
