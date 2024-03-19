package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;
public class BudgetStepDefinition {
    User haveBudgetOrginizer;
    Event budgetEvent;
    Vendor associatedVendor;
    Venue associatedVenue;
    public static final String USER = "User";

    public BudgetStepDefinition(){
        haveBudgetOrginizer = new User("hello@email", "123", USER);
        haveBudgetOrginizer.setBudget(10_000);
        budgetEvent = new Event("hello", "hello", "hello", "hello", "hello", 10, haveBudgetOrginizer);
        associatedVendor = new Vendor("loc@gmail.com","123", "singers", "salfeet",
                3000, 2, "my price is 1000 brother take it or leave it");
        associatedVenue = new Venue("hello", 20, "am", 5000);


        budgetEvent.setAssociatedVenue(associatedVenue);
        budgetEvent.setVendor(associatedVendor);
        haveBudgetOrginizer.setAsOrganizer();

    }
    @Given("I am an organizer4")
    public void iAmAnOrganizer4() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(haveBudgetOrginizer.isOrganizer());

    }
    @When("I view the budget report for an event")
    public void iViewTheBudgetReportForAnEvent() {
        budgetEvent.printReport();
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the system should display a report with all the venues and the vendors associated with the event and the remaining budget")
    public void theSystemShouldDisplayAReportWithAllTheVenuesAndTheVendorsAssociatedWithTheEvent() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(this.haveBudgetOrginizer.budget >= 0 && this.haveBudgetOrginizer.budget <= 10_000);
    }

    @Given("I don't have a sufficient budget for venue")
    public void iDonTHaveASufficientBudgetForVenue() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I try to assign a venue")
    public void iTryToAssignAVenue() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the venue won't be associated with the event")
    public void theVenueWonTBeAssociatedWithTheEvent() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("I don't have a sufficient budget for vendor")
    public void iDonTHaveASufficientBudgetForVendor() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("I try to assign a vendor")
    public void iTryToAssignAVendor() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the vendor won't be associated with the event")
    public void theVendorWonTBeAssociatedWithTheEvent() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("I have sufficient budget for vendor")
    public void iHaveSufficientBudgetForVendor() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Given("I have sufficient budget for venue")
    public void iHaveSufficientBudgetForVenue() {
        // Write code here that turns the phrase above into concrete actions
    }
    @Then("the venue and vendor will be associated with the event")
    public void theVenueAndVendorWillBeAssociatedWithTheEvent() {
        // Write code here that turns the phrase above into concrete actions
    }



}
