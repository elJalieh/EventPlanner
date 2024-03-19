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
        //System.out.println("welcome");

    }

    @When("the email is invalid email is {string} and password is {string}")
    public void theEmailIsInvalidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        if(login.isValid(email, password ) == 0){
            login.logInStatus = false;
        }
        assertFalse(login.logInStatus);


    }
    @Then("user failed in log in")
    public void userFailedInLogIn() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("the password is invalid email is {string} and password is {string}")
    public void thePasswordIsInvalidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        if(login.isValid(email, password) == 0){
            login.logInStatus = false;
        }
        assertFalse(login.logInStatus);

    }

    @When("the information is invalid, email is {string} and password is {string}")
    public void theInformationAreInvalidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        if(login.isValid(email, password) == 0){
            login.logInStatus = false;
        }
        assertFalse(login.logInStatus);


    }

    @When("the information exists, the email is {string}")
    public void theInformationExistsTheEmailIs(String email) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(login.emailExists(email));
    }
    @Then("signing up fails")
    public void signingUpFails() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the email format is incorrect")
    public void theEmailFormatIsIncorrect() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("the information exists, the email is not {string}")
    public void theInformationExistsTheEmailIsNot(String email) {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(login.emailExists(email));
    }
    @Then("signing up succeeds")
    public void signingUpSucceeds() {
        // Write code here that turns the phrase above into concrete actions
    }

}
