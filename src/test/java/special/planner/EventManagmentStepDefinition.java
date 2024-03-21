package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class EventManagmentStepDefinition {
private final special.planner.EventManagement eventManagement;
User currentUser;
User attendeeUser1;
User attendeeUser2;
Event newEvent;
int eventId;
String newdate;
String newtime ;
String newlocation ;
String newtheme ;
String newdescription;
int newattendeeCount;
Event eventToBeEdited;
Event eventToBeDeleted;

   public EventManagmentStepDefinition(EventManagement eventManagement){
        this.eventManagement = eventManagement;
        currentUser = new User("email@email", "123", "User");
        attendeeUser1 = new User("attendee user1", "123", "User");
        attendeeUser2 = new User("attendee user2", "123", "User");
        eventToBeEdited = new Event("hello", "hello", "hello", "hello", "hello", 10, currentUser);
        eventToBeDeleted = new Event("hello", "hello", "hello", "hello", "hello", 10, currentUser);


    }
    @Given("I am an organizer")
    public void iAmAnOrganizer() {
        // Write code here that turns the phrase above into concrete actions
        currentUser.setAsOrganizer();
        assertTrue(currentUser.Organizer);

    }
    @When("I create a new event with the following details:")
    public void iCreateANewEventWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Assuming you have getters for these properties in your Event class
        String date = data.get(0).get("Date");
        String time = data.get(0).get("Time");
        String location = data.get(0).get("Location");
        String theme = data.get(0).get("Theme");
        String description = data.get(0).get("Description");
        int attendeeCount = Integer.parseInt(data.get(0).get("Attendee Count"));

        newEvent = new Event(date, time, location, theme, description, attendeeCount, currentUser);
        eventManagement.addEvent(newEvent);
    }
    @When("I manage the guest list for the event")
    public void iManageTheGuestListForTheEvent() {
        // Write code here that turns the phrase above into concrete actions
       newEvent.addAttendee(attendeeUser1);
       newEvent.addAttendee(attendeeUser2);
    }
    @Then("the new event {string} should be added to the event list")
    public void theNewEventShouldBeAddedToTheEventList(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(eventManagement.isEventInList(newEvent));
    }
    @When("I edit the event details for {string} as follows:")
    public void iEditTheEventDetailsForAsFollows(String string, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Assuming you have getters for these properties in your Event class
         newdate = data.get(0).get("Date");
         newtime = data.get(0).get("Time");
         newlocation = data.get(0).get("Location");
         newtheme = data.get(0).get("Theme");
         newdescription = data.get(0).get("Description");
         newattendeeCount = Integer.parseInt(data.get(0).get("Attendee Count"));
        eventToBeEdited.updateEvent(newdate, newtime, newlocation, newtheme, newdescription, newattendeeCount);
    }
    @When("I manage the updated guest list for the event")
    public void iManageTheUpdatedGuestListForTheEvent() {
        // Write code here that turns the phrase above into concrete actions
        eventToBeEdited.addAttendee(attendeeUser2);
        eventToBeEdited.removeAttendee(attendeeUser2);
    }
    @Then("the event details for {string} should be updated in the event list")
    public void theEventDetailsForShouldBeUpdatedInTheEventList(String string) {
        // Write code here that turns the phrase above into concrete actions
        if(eventToBeEdited.EventTime.equals(newtime) && eventToBeEdited.EventLocation.equals(newlocation)
        && eventToBeEdited.EventDescription.equals(newdescription) && eventToBeEdited.EventDate.equals(newdate)
        && (eventToBeEdited.AttendeeCount == newattendeeCount) && eventToBeEdited.EventTheme.equals(newtheme)){
        }
        else fail();
    }
    @When("I delete the event {string}")
    public void iDeleteTheEvent(String string) {
        // Write code here that turns the phrase above into concrete actions
        eventManagement.addEvent(eventToBeDeleted);
        eventManagement.deleteEvent(eventToBeDeleted);
    }
    @Then("the event {string} should be removed from the event list")
    public void theEventShouldBeRemovedFromTheEventList(String string) {
        // Write code here that turns the phrase above into concrete actions
        assertFalse(eventManagement.isEventInList(eventToBeDeleted));
    }

}
