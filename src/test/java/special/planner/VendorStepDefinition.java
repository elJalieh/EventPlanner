package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.*;
public class VendorStepDefinition {
    private final VendorManagement vendorManagement;

    User currentUser;
    Vendor newVendor;


    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public VendorStepDefinition(VendorManagement vendorManagement){
        this.vendorManagement=vendorManagement;
        currentUser = new User("email@email", "123", "user");
        currentUser.setAsOrganizer();

        String Location = "City Center";
        String Availability ="Available";
        int Pricing= 500;
        float Reviews = 4.5F;
        newVendor = new Vendor(Location,Availability,Pricing,Reviews);
        vendorManagement.addVendor(newVendor);

    }
    @Given("I am an organizer3")
    public void iAmAnOrganizer3() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(currentUser.isOrganizer()==true);

    }
    @When("I search and filter vendors for the following criteria:")
    public void iSearchAndFilterVendorsForTheFollowingCriteria(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        vendorManagement.searchVendor(data.get(0).get("Location"),data.get(0).get("Availability"),Integer.parseInt(data.get(0).get("Pricing")),Float.parseFloat(data.get(0).get("Reviews")));

    }
    @Then("the list of vendors should be displayed")
    public void theListOfVendorsShouldBeDisplayed() {
       if(vendorManagement.x!=null){
           vendorManagement.display();
       }
       else{
        LOGGER.info("this vendors is not existing");
       }

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


