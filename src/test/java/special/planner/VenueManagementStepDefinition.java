package special.planner;
import io.cucumber.java.en.*;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class VenueManagementStepDefinition {
    public static final String admin = "Admin";
    public static final String user = "User";
    private final VenueManagement venueManagement;
    User currentUserAdmin;
    User currentUserOrg;
    Venue newVenue;
    Venue venueToBeEdited;
    String newVenueName;
    int newCapacity;
    String newAmenities;
    int newPricing;
    Venue venueToBeDeleted;
    Venue venueToBeAssociated;
    Event associatedEvent;
    public VenueManagementStepDefinition(VenueManagement venueManagement){
        this.venueManagement = venueManagement;
        currentUserAdmin = new User("email@email", "123", admin);
        venueToBeEdited = new Venue("hello", 20, "am", 500);
         venueToBeDeleted = new Venue("hello", 20, "am", 500);
        venueToBeAssociated = new Venue("hello", 20, "am", 500);
        currentUserOrg = new User("hello@email", "123", user);
        associatedEvent = new Event("date", "time", "location", "theme", "desc", 50, currentUserOrg);
        venueManagement.initializeVenues();
    }

    @Given("I am an admin")
    public void iAmAnAdmin() {
        assertTrue(currentUserAdmin.isAdmin());
    }
    @When("I add a new venue with the following details:")
    public void iAddANewVenueWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Assuming you have getters for these properties in your Event class
        String venueName = data.getFirst().get("Venue Name");
        String amenities = data.getFirst().get("Amenities");
        int pricing = Integer.parseInt(data.getFirst().get("Pricing"));
        int capacity = Integer.parseInt(data.getFirst().get("Capacity"));

        newVenue = new Venue(venueName, capacity, amenities, pricing);
        venueManagement.addVenue(newVenue);
    }
    @Then("the new venue {string} should be added to the venue list")
    public void theNewVenueShouldBeAddedToTheVenueList(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(venueManagement.isVenueInList(newVenue));
        venueManagement.displayVenues();
    }

    @When("I edit the details for the venue {string} as follows:")
    public void iEditTheDetailsForTheVenueAsFollows(String string, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Assuming you have getters for these properties in your Event class
         newVenueName = data.getFirst().get("Venue Name");
         newCapacity = Integer.parseInt(data.getFirst().get("Capacity"));
         newAmenities = data.getFirst().get("Amenities");
         newPricing = Integer.parseInt(data.getFirst().get("Pricing"));

        venueToBeEdited.editVenue(newVenueName, newCapacity, newAmenities, newPricing);
    }
    @Then("the details for the venue {string} should be updated in the venue list")
    public void theDetailsForTheVenueShouldBeUpdatedInTheVenueList(String string) {
        // Write code here that turns the phrase above into concrete actions
        if(!(venueToBeEdited.venueName.equals(newVenueName) && venueToBeEdited.amenities.equals(newAmenities)
                && (venueToBeEdited.capacity == newCapacity) && (venueToBeEdited.pricing == newPricing))){
            fail();
        }
    }
    @When("I delete the venue {string}")
    public void iDeleteTheVenue(String string) {
        // Write code here that turns the phrase above into concrete actions
        venueManagement.addVenue(venueToBeDeleted);
        venueManagement.deleteVenue(venueToBeDeleted);


    }
    @Then("the venue {string} should be removed from the venue list")
    public void theVenueShouldBeRemovedFromTheVenueList(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(venueManagement.isVenueInList(venueToBeDeleted));
    }
    @Given("I am an organizer2")
    public void iAmAnOrganizer2() {
        // Write code here that turns the phrase above into concrete actions

        currentUserOrg.setAsOrganizer();

        assertTrue(currentUserOrg.isOrganizer());
    }
    @When("I link the venue {string} with the event {string}")
    public void iLinkTheVenueWithTheEvent(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        venueToBeAssociated.setAssociatedEvent(associatedEvent);
        venueToBeAssociated.setAssociatedEvent(associatedEvent);
        associatedEvent.setAssociatedVenue(venueToBeAssociated);

    }
    @Then("the venue {string} should be associated with the event {string} in the system")
    public void theVenueShouldBeAssociatedWithTheEventInTheSystem(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(venueToBeAssociated.getAssociatedEvent(), associatedEvent);
    }
}
