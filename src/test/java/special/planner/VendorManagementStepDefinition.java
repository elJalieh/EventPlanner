package special.planner;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class VendorManagementStepDefinition {
    public static final String USER = "User";
    private final Login login;
    Vendor vendorByLocation;
    Vendor vendorByPrice;
    Vendor vendorByAvailability;
    Vendor vendorByReview;
    Vendor vendorPackage;
    Vendor vendorRequest;
    User currentUserOrg;
    String email;
    Event associatedEvent;


    public VendorManagementStepDefinition(Login login) {
        this.login = login;
        currentUserOrg = new User("hello@email", "123", USER);
        currentUserOrg.setAsOrganizer();
        currentUserOrg.setBudget(1_000_000);
        vendorByLocation = new Vendor("loc@gmail.com","123", "singers", "salfeet",
                500, 2, "my price is 1000 brother take it or leave it");
        vendorByLocation.setAvailability(false);
        vendorByPrice = new Vendor("price@gmail.com","123", "singers", "asdf",
                1000, 3, "my price is 1000 brother take it or leave it");
        vendorByPrice.setAvailability(false);
        vendorByReview = new Vendor("rev@gmail.com","123", "singers", "werg",
                2000, 4, "my price is 1000 brother take it or leave it");
        vendorByReview.setAvailability(false);
        vendorByAvailability = new Vendor("avail@gmail.com","123", "singers", "ewr",
                595858048, 5, "my price is 1000 brother take it or leave it");
        vendorPackage = new Vendor("package@gmail.com","123", "singers", "wow",
                2345, 1, "my price is 1000 brother take it or leave it");

        vendorRequest = new Vendor("requist@gmail.com","123", "singers", "woah woah woah",
                4444, 0, "my price is 1000 brother take it or leave it");
        associatedEvent = new Event("date", "time", "location", "theme", "desc", 50, currentUserOrg);



        vendorPackage.addPackage("cookies");
        vendorPackage.addPackage("cola");
        vendorPackage.addPackage("m3aleq wo s7oon");
        vendorPackage.addPackage("sharashif");

        login.addVendor(vendorByLocation);
        login.addVendor(vendorByPrice);
        login.addVendor(vendorByReview);
        login.addVendor(vendorByAvailability);

    }

    @Given("I am an organizer3")
    public void iAmAnOrganizer3() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(currentUserOrg.isOrganizer());
    }

    @When("choose to display all vendors")
    public void chooseToDisplayAllVendors() {

    }

    @Then("the list of vendors appears.")
    public void theListOfVendorsAppears() {
        login.displayAllVendors();

    }
    @When("I search for the vendor by location {string}")
    public void iSearchForTheVendorByLocation(String location) {
          email = login.displayVendorByLocation(location);
    }
    @Then("the list of vendors for the location appears.")
    public void theListOfVendorsForTheLocationAppears() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("loc@gmail.com", email);

    }

    @When("I search for the vendor by the price {int}")
    public void iSearchForTheVendorByThePrice(Integer price) {
        // Write code here that turns the phrase above into concrete actions
        email = login.displayVendorByPrice( price);

    }
    @Then("the list of vendors for the price appears.")
    public void theListOfVendorsForThePriceAppears() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("price@gmail.com", email);
    }
    @When("I search for the vendor by availability true")
    public void iSearchForTheVendorByAvailabilityTrue() {
        // Write code here that turns the phrase above into concrete actions
        email = login.displayVendorByAvailability(true);


    }
    @Then("the list of vendors for the availability appears.")
    public void theListOfVendorsForTheAvailabilityAppears() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("avail@gmail.com", email);
    }

    @When("I search for the vendor by the review {int}")
    public void iSearchForTheVendorByTheReview(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        email = login.displayVendorByReview(int1);
    }
    @Then("the list of vendors for that review appears.")
    public void theListOfVendorsForThatReviewAppears() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("rev@gmail.com", email);

    }

    @When("I negotiate the contract terms with the vendor")
    public void iNegotiateTheContractTermsWithTheVendor() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(vendorPackage.getContractDescription());
    }
    @When("I accept the contract")
    public void iAcceptTheContract() {
        // Write code here that turns the phrase above into concrete actions
        currentUserOrg.linkWithVendor( vendorPackage);
    }
    @When("I have booked the vendor")
    public void iHaveBookedTheVendor() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(vendorPackage.isAvailable());
    }

    @When("I request a package {int} from the vendor for an event after displaying them")
    public void iRequestAPackageFromTheVendorForAnEvent(int packageIndex) {
        // Write code here that turns the phrase above into concrete actions
        vendorPackage.displayPackages();
        associatedEvent.setPackage(vendorPackage.getPackageName(packageIndex, currentUserOrg));
        associatedEvent.setVendor(vendorPackage);
    }
    @Then("the vendor with his packets will be booked by me")
    public void thePacketWillBeBookedForMe() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(vendorPackage.booker.email, currentUserOrg.email);
        assertEquals(associatedEvent.vendorPackages, vendorPackage.vendorPackages.get(2));
       assertEquals(associatedEvent.eventVendor, vendorPackage);
    }

    @When("I choose to release the vendor from my event")
    public void iChooseToReleaseTheVendorFromMyEvent() {
        associatedEvent.setVendor(vendorPackage);
        associatedEvent.releaseVendor();
    }


    @Then("The vendor will be released and my event will have no vendor set")
    public void theVendorWillBeReleasedAndMyEventWillHaveNoVendorSet() {
        assertNull(associatedEvent.eventVendor);
    }
}

