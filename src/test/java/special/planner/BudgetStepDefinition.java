package special.planner;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class BudgetStepDefinition {
    User haveBudgetOrganizer;
    User doesntHaveBudgetOrganizer;
    Event budgetEvent;
    Event nobudgetEvent;
    Vendor associatedVendor;
    Venue associatedVenue;
    EventManagement eventManagement;
    public static final String USER = "User";

    public BudgetStepDefinition(){
        haveBudgetOrganizer = new User("hello@email", "123", USER);
        doesntHaveBudgetOrganizer = new User("hello@email", "123", USER);
        haveBudgetOrganizer.setAsOrganizer();
        doesntHaveBudgetOrganizer.setAsOrganizer();
        haveBudgetOrganizer.setBudget(10_000);
        doesntHaveBudgetOrganizer.setBudget(2_000);
        budgetEvent = new Event("hello", "hello", "hello", "hello", "hello", 10, haveBudgetOrganizer);
        nobudgetEvent = new Event("hsdello", "2hello", "2hello", "2hello", "2hello", 12, doesntHaveBudgetOrganizer);
        associatedVendor = new Vendor("loc@gmail.com","123", "singers", "salfeet",
                3000, 2, "my price is 1000 brother take it or leave it");
        associatedVenue = new Venue("hello", 20, "am", 5000);
        eventManagement = new EventManagement();
        eventManagement.addEvent(budgetEvent);
        eventManagement.addEvent(nobudgetEvent);
    }
    @Given("I am an organizer4")
    public void iAmAnOrganizer4() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(haveBudgetOrganizer.isOrganizer());
        budgetEvent.setAssociatedVenue(associatedVenue);
        budgetEvent.setVendor(associatedVendor);

    }
    @When("I view the budget report for an event")
    public void iViewTheBudgetReportForAnEvent() {
        budgetEvent.printReport();
        budgetEvent.releaseVendor();
        budgetEvent.releaseVenue();
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the system should display a report with all the venues and the vendors associated with the event and the remaining budget")
    public void theSystemShouldDisplayAReportWithAllTheVenuesAndTheVendorsAssociatedWithTheEvent() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(2_000, this.haveBudgetOrganizer.budget);
        eventManagement.printEventsReports(haveBudgetOrganizer);
    }

    @Given("I don't have a sufficient budget for venue")
    public void iDonTHaveASufficientBudgetForVenue() {
        // Write code here that turns the phrase above into concrete actions
        doesntHaveBudgetOrganizer.setBudget(4_000);



    }
    @When("I try to assign a venue")
    public void iTryToAssignAVenue() {
        // Write code here that turns the phrase above into concrete actions
        nobudgetEvent.setVendor(associatedVendor);
        nobudgetEvent.setAssociatedVenue(associatedVenue);

    }
    @Then("the venue won't be associated with the event")
    public void theVenueWonTBeAssociatedWithTheEvent() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(1_000, doesntHaveBudgetOrganizer.budget);

    }

    @Given("I don't have a sufficient budget for vendor")
    public void iDonTHaveASufficientBudgetForVendor() {
        // Write code here that turns the phrase above into concrete actions
        doesntHaveBudgetOrganizer.setBudget(6_500);
        nobudgetEvent.releaseVendor();
        nobudgetEvent.releaseVenue();

    }
    @When("I try to assign a vendor")
    public void iTryToAssignAVendor() {
        // Write code here that turns the phrase above into concrete actions
        nobudgetEvent.setAssociatedVenue(associatedVenue);
        nobudgetEvent.setVendor(associatedVendor);
    }
    @Then("the vendor won't be associated with the event")
    public void theVendorWonTBeAssociatedWithTheEvent() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(1_500, doesntHaveBudgetOrganizer.budget);

    }

    @Given("I have sufficient budget for vendor")
    public void iHaveSufficientBudgetForVendor() {
        // Write code here that turns the phrase above into concrete actions
        haveBudgetOrganizer.setBudget(1_000_000);
        budgetEvent.setOrganizer(haveBudgetOrganizer);
        budgetEvent.releaseVenue();
        budgetEvent.releaseVendor();
    }
    @Given("I have sufficient budget for venue and vendor")
    public void iHaveSufficientBudgetForVenue() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(haveBudgetOrganizer.budget >= (associatedVendor.pricing+associatedVenue.pricing));
    }
    @Then("the venue and vendor will be associated with the event")
    public void theVenueAndVendorWillBeAssociatedWithTheEvent() {
        // Write code here that turns the phrase above into concrete actions
        budgetEvent.setVendor(associatedVendor);
        budgetEvent.setAssociatedVenue(associatedVenue);
        budgetEvent.printReport();
    }
}
