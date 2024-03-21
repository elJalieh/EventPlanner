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
public class CalendarStepDefinition {
    User currentUserOrg;
    User attendee;
    public static final String USER = "User";
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    Event eventUpComing1Day;
    Event eventUpComing3Days;
    Event oldEvent;
    private final special.planner.EventManagement eventManagement;

    public CalendarStepDefinition(EventManagement eventManagement){
        this.eventManagement = eventManagement;
        currentUserOrg = new User("org@email", "123", USER);
        attendee = new User("att@email", "123", USER);
        currentUserOrg.setAsOrganizer();
        currentUserOrg.setBudget(1_000_000);
        String tommorrow = CalendarUtil.getDateAfterDays(1);
        String after3Days = CalendarUtil.getDateAfterDays(3);
        String yesterday = CalendarUtil.getDateAfterDays(-1);
        eventUpComing1Day = new Event(tommorrow, "hello1", "hello1", "hello1", "hello1", 10, currentUserOrg);
        eventUpComing3Days = new Event(after3Days, "hello2", "hello2", "hello2", "hello2", 10, currentUserOrg);
        oldEvent = new Event(yesterday, "hello3", "hello3", "hello3", "hello3", 10, currentUserOrg);
        eventUpComing1Day.addAttendee(attendee);
        eventUpComing3Days.addAttendee(attendee);
        oldEvent.addAttendee(attendee);
        eventManagement.addEvent(eventUpComing1Day);
        eventManagement.addEvent(eventUpComing3Days);
        eventManagement.addEvent(oldEvent);


    }

    @Given("I am user\\/organizer")
    public void iAmUserOrganizer() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(currentUserOrg.isOrganizer());
        assertTrue(attendee instanceof User);
    }
    @When("I view the calendar")
    public void iViewTheCalendar() {
        //pick the upcoming events from the main menue
    }
    @Then("I should see a list of upcoming events associated with me")
    public void iShouldSeeAListOfUpcomingEventsAssociatedWithMe() {
        LOGGER.info("up coming events are: \n");
        eventManagement.displayUpComingEvents(attendee);
    }

    @When("the reminder time approaches within 2 days")
    public void theReminderTimeApproachesWithinDays() {

    }
    @Then("I should see a reminder in the reminders interface")
    public void iShouldSeeAReminderInTheRemindersInterface() {
        // Write code here that turns the phrase above into concrete actions
        LOGGER.info("up coming events within 2 days are: \n");
        eventManagement.displayEventsWithin2Days(attendee);
    }
}
