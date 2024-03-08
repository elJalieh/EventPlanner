package special.planner;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;


public class AdminStepDefinition {
    private final special.planner.Login login;
    User currentUser;
    User testUser;
    User testUser1;


    public AdminStepDefinition(Login login){
        this.login = login;
        currentUser = new User("email@email", "123", "Admin");
        testUser = new User("s12112161@stu.najah.edu", "123", "Service Provider");
        testUser1 = new User("s12112161@stu.najah.edu", "123", "Service Provider");
    }


    @Given("I logged in as an admin")
    public void iLoggedInAsAnAdmin() {
        // Write code here that turns the phrase above into concrete actions
        currentUser.setAsAdmin();
        assertTrue(currentUser.Admin);
    }
    @When("I create an account with {string}, {string}, and {string} as the following")
    public void iCreateAnAccountWithAndAsTheFollowing(String email, String password, String role, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

        login.addServiceProvider(testUser.email, testUser.password);


    }
    @Then("I should see a success message confirming the account creation")
    public void iShouldSeeASuccessMessageConfirmingTheAccountCreation() {
        // Write code here that turns the phrase above into concrete actions

        assertTrue(login.emailExists(testUser.email));

    }


    @When("I delete the account with email {string}")
    public void iDeleteTheAccountWithEmail(String email) {
        // Write code here that turns the phrase above into concrete actions
        login.addServiceProvider(testUser1.email, testUser1.password);
        login.deleteUser(testUser1.email);

    }
    @Then("I should see a success message confirming the account deletion")
    public void iShouldSeeASuccessMessageConfirmingTheAccountDeletion() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(login.emailExists(testUser1.email));
    }

}
