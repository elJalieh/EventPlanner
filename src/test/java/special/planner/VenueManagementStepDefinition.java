package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;
public class VenueManagementStepDefinition {
    @Given("I am an admin")
    public void iAmAnAdmin() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I add a new venue with the following details:")
    public void iAddANewVenueWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
    }
    @Then("the new venue {string} should be added to the venue list")
    public void theNewVenueShouldBeAddedToTheVenueList(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I edit the details for the venue {string} as follows:")
    public void iEditTheDetailsForTheVenueAsFollows(String string, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
    }
    @Then("the details for the venue {string} should be updated in the venue list")
    public void theDetailsForTheVenueShouldBeUpdatedInTheVenueList(String string) {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I delete the venue {string}")
    public void iDeleteTheVenue(String string) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the venue {string} should be removed from the venue list")
    public void theVenueShouldBeRemovedFromTheVenueList(String string) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Given("I am an organizer2")
    public void iAmAnOrganizer2() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I link the venue {string} with the event {string}")
    public void iLinkTheVenueWithTheEvent(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the venue {string} should be associated with the event {string} in the system")
    public void theVenueShouldBeAssociatedWithTheEventInTheSystem(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }
}
