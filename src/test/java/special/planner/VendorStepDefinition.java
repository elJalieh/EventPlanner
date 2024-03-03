package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;
public class VendorStepDefinition {
    @When("I add a new vendor to the database with the following details:")
    public void iAddANewVendorToTheDatabaseWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
    }
    @Then("the new vendor {string} should be added to the vendor database")
    public void theNewVendorShouldBeAddedToTheVendorDatabase(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("I am an organizer3")
    public void iAmAnOrganizer3() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I search and filter vendors for the venue {string} with the following criteria:")
    public void iSearchAndFilterVendorsForTheVenueWithTheFollowingCriteria(String string, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
    }
    @Then("the list of vendors should be displayed based on the specified criteria for the venue {string}")
    public void theListOfVendorsShouldBeDisplayedBasedOnTheSpecifiedCriteriaForTheVenue(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I request a package from the vendor {string} for the event {string}")
    public void iRequestAPackageFromTheVendorForTheEvent(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the vendor should receive a package request notification")
    public void theVendorShouldReceiveAPackageRequestNotification() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I negotiate the contract terms with the vendor {string} for the event {string}")
    public void iNegotiateTheContractTermsWithTheVendorForTheEvent(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the contract terms should be updated in the system, and the vendor should receive a notification")
    public void theContractTermsShouldBeUpdatedInTheSystemAndTheVendorShouldReceiveANotification() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I book the vendor {string} for the event {string}")
    public void iBookTheVendorForTheEvent(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the vendor should be marked as booked for the specified event in the system")
    public void theVendorShouldBeMarkedAsBookedForTheSpecifiedEventInTheSystem() {
        // Write code here that turns the phrase above into concrete actions
    }


}
