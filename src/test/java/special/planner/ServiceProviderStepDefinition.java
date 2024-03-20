package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
public class ServiceProviderStepDefinition {

    private final special.planner.Login login;
    Vendor v ;
    String package0;

    public ServiceProviderStepDefinition(Login login){
        this.login = login;
        v = new Vendor("Ayman","123", "Singer", "Salfit",
                4000, 5, "my price is 4000 brother take it or leave it");
        v.addPackage("Cristiano Ronaldo");

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
        v.Packages.add(package0);
    }
    @Then("the package should be added to the list")
    public void thePackageShouldBeAddedToTheList() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(v.isPackageInlist(package0));
    }

    @When("I choose to edit an existing package")
    public void iChooseToEditAnExistingPackage() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the package should be updated in the list")
    public void thePackageShouldBeUpdatedInTheList() {
        // Write code here that turns the phrase above into concrete actions

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
        assertFalse(v.Packages.contains(package0));
    }

    @When("I decide to make changes to my contract")
    public void iDecideToMakeChangesToMyContract() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("a new contract should be created")
    public void aNewContractShouldBeCreated() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I choose to view my list of packages")
    public void iChooseToViewMyListOfPackages() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("a list for my packages will be displayed")
    public void aListForMyPackagesWillBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("I choose to view my customer details")
    public void iChooseToViewMyCustomerDetails() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the customer details should be displayed")
    public void theCustomerDetailsShouldBeDisplayed() {
        // Write code here that turns the phrase above into concrete actions

    }
}
