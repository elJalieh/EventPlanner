package special.planner;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class loginStepDefinition {

    private final Login login;
    User currentUser;

    public loginStepDefinition(Login login){
        this.login = login;
        currentUser = new User("user@email", "123", "user");
        login.users.add(currentUser);
    }
    @Given("that the user is not logged in")
    public void thatTheUserIsNotLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
        login.initializeUsers();
        assertFalse(login.isLoggedIn());
    }
    @When("the information is valid email is {string} and password is {string}")
    public void theInformationIsValidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        if(login.getTypeNumber(email, password ) == 1 || login.getTypeNumber(email, password ) == 2){
            login.setLogInStatus(true);
        }
    }
    @Then("user successfully log in")
    public void userSuccessfullyLogIn() {
        // Write code here that turns the phrase above into concrete actions
        //System.out.println("welcome");
        assertTrue(login.isLoggedIn());
        assertEquals(currentUser, login.getCurrentUser("user@email", "123"));
    }

    @When("the email is invalid email is {string} and password is {string}")
    public void theEmailIsInvalidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        if(login.getTypeNumber(email, password ) == 0){
            login.setLogInStatus(false);
        }
    }
    @Then("user failed in log in")
    public void userFailedInLogIn() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(login.isLoggedIn());
    }

    @When("the password is invalid email is {string} and password is {string}")
    public void thePasswordIsInvalidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        if(login.getTypeNumber(email, password) == 0){
            login.setLogInStatus(false);
        }
        assertFalse(login.isLoggedIn());
    }

    @When("the information is invalid, email is {string} and password is {string}")
    public void theInformationAreInvalidEmailIsAndPasswordIs(String email, String password) {
        // Write code here that turns the phrase above into concrete actions
        if(login.getTypeNumber(email, password) == 0){
            login.setLogInStatus(false);
        }
        assertFalse(login.isLoggedIn());
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
        login.addUser(email, "123");
    }
    @Then("signing up succeeds")
    public void signingUpSucceeds() {
        // Write code here that turns the phrase above into concrete actions
    }

}
