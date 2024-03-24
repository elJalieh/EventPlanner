package special.planner;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class EventManagementStepDefinition {
private final special.planner.EventManagement eventManagement;
User currentUser;
User attendeeUser1;
User attendeeUser2;
Event newEvent;
String newDate;
String newTime ;
String newLocation ;
String newTheme ;
String newDescription;
int newAttendeeCount;
Event eventToBeEdited;
Event eventToBeDeleted;

   public EventManagementStepDefinition(EventManagement eventManagement){
        this.eventManagement = eventManagement;
        currentUser = new User("email@email", "123", "User");
        attendeeUser1 = new User("attendee user1", "123", "User");
        attendeeUser2 = new User("attendee user2", "123", "User");
        eventToBeEdited = new Event("hello", "hello", "hello", "hello", "hello", 10, currentUser);
        eventToBeDeleted = new Event("hello", "hello", "hello", "hello", "hello", 10, currentUser);
        eventManagement.addEvent(eventToBeEdited);
        eventManagement.printEvents();



    }
    @Given("I am an organizer")
    public void iAmAnOrganizer() {
        // Write code here that turns the phrase above into concrete actions
        currentUser.setAsOrganizer();
        assertTrue(currentUser.organizer);
        assertTrue(eventManagement.isOrganizerOfEvent(currentUser));
        assertFalse(eventManagement.isOrganizerOfEvent(attendeeUser1));

    }
    @When("I create a new event with the following details:")
    public void iCreateANewEventWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Assuming you have getters for these properties in your Event class
        String date = data.getFirst().get("Date");
        String time = data.getFirst().get("Time");
        String location = data.getFirst().get("Location");
        String theme = data.getFirst().get("Theme");
        String description = data.getFirst().get("Description");
        int attendeeCount = Integer.parseInt(data.getFirst().get("Attendee Count"));

        newEvent = new Event(date, time, location, theme, description, attendeeCount, currentUser);
        assertFalse(newEvent.isNotTheOrganizerOfTheEvent(currentUser));
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
        eventManagement.displayEventsForOrganizer(currentUser);
         newDate = data.getFirst().get("Date");
         newTime = data.getFirst().get("Time");
         newLocation = data.getFirst().get("Location");
         newTheme = data.getFirst().get("Theme");
         newDescription = data.getFirst().get("Description");
         newAttendeeCount = Integer.parseInt(data.getFirst().get("Attendee Count"));
        eventToBeEdited.updateEvent(newDate, newTime, newLocation, newTheme, newDescription, newAttendeeCount);
    }
    @When("I manage the updated guest list for the event")
    public void iManageTheUpdatedGuestListForTheEvent() {
        // Write code here that turns the phrase above into concrete actions
        eventToBeEdited.addAttendee(attendeeUser2);
        eventToBeEdited.removeAttendee(attendeeUser2);
    }
    @Then("the event details for {string} should be updated in the event list and displayed")
    public void theEventDetailsForShouldBeUpdatedInTheEventList(String string) {
        // Write code here that turns the phrase above into concrete actions
        if(eventToBeEdited.eventTime.equals(newTime) && eventToBeEdited.eventLocation.equals(newLocation)
        && eventToBeEdited.eventDescription.equals(newDescription) && eventToBeEdited.eventDate.equals(newDate)
        && (eventToBeEdited.attendeeCount == newAttendeeCount) && eventToBeEdited.eventTheme.equals(newTheme)){
            eventManagement.printEventsForOrganizer(currentUser);
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
