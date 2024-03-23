package special.planner;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class ServiceProviderStepDefinition {

    private final special.planner.Login login;
    Vendor v ;
    String package0;
    User u;
    Event e;
    int index = -1;
    public ServiceProviderStepDefinition(Login login){
        this.login = login;
        v = new Vendor("Ayman","123", "Singer", "Salfit",
                4000, 5, "my price is 4000 brother take it or leave it");
        v.addPackage("Cristiano Ronaldo");
        v.addPackage("Ankara messi");
        v.addPackage("Juventus");

        u = new User("u","123", "User");
        e = new Event("Event", "hhhh", "Nablus", "World Cup", "hello", 50, u);

        e.eventVendor = v;
    }


    @Given("I am Service Provider")
    public void iAmServiceProvider() {
        // Write code here that turns the phrase above into concrete actions
        login.vendors.add(v);
        assertEquals(2, login.isValid(v.email,v.password));
    }
    @When("I choose to add a new package to my list")
    public void iChooseToAddANewPackageToMyList() {
        // Write code here that turns the phrase above into concrete actions
        package0 = "يلا خيار يلا بندورة يلا باذنجان الكيلو ب10 شيكل";
        v.vendorPackages.add(package0);
    }
    @Then("the package should be added to the list")
    public void thePackageShouldBeAddedToTheList() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(v.isPackageInlist(package0));
    }

    @When("I choose to edit an existing package")
    public void iChooseToEditAnExistingPackage() {
        // Write code here that turns the phrase above into concrete actions
        index = v.getPackageNum("Cristiano Ronaldo");
        v.vendorPackages.set(index,"Edited Package");
    }
    @Then("the package should be updated in the list")
    public void thePackageShouldBeUpdatedInTheList() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("Edited Package",v.vendorPackages.get(index));
    }

    @When("I choose to delete an existing package")
    public void iChooseToDeleteAnExistingPackage() {
        // Write code here that turns the phrase above into concrete actions
        package0 = "Cristiano Ronaldo";
        v.deletePackage(package0);
    }
    @Then("the package should be deleted from the list")
    public void thePackageShouldBeDeletedFromTheList() {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(v.vendorPackages.contains(package0));
    }

    @When("I decide to make changes to my contract")
    public void iDecideToMakeChangesToMyContract() {
        // Write code here that turns the phrase above into concrete actions
        v.contractDescription = "New contract";
    }
    @Then("a new contract should be created")
    public void aNewContractShouldBeCreated() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("New contract",v.contractDescription);
    }

    @When("I choose to view my list of packages")
    public void iChooseToViewMyListOfPackages() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("a list for my packages will be displayed")
    public void aListForMyPackagesWillBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(v.viewPackages());
    }

    @When("I choose to view my customer details")
    public void iChooseToViewMyCustomerDetails() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the customer details should be displayed if there's an active deal")
    public void theCustomerDetailsShouldBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions
        v.booker = u;
        e.setVendor(v);
        v.setEvent(e);
        assertTrue(v.checkBooker());
    }
    @Then("the customer details should not be displayed if there's no active deal")
    public void theCustomerDetailsShouldNotBeDisplayedIfThereSNoActiveDeal() {
        // Write code here that turns the phrase above into concrete actions
        v.booker = null;
        v.releaseEvent();
        assertFalse(v.checkBooker());
        assertNull(v.vendorEvent);
    }
}
