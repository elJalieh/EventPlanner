package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventManagement {
    private static final Logger LOGGER = Logger.getLogger(EventManagement.class.getName());
    public static final int DATED = 0;
    public static final int WITHIN_2_DAYS = 1;
    List<Event> events = new ArrayList<>();
    public void addEvent(Event newEvent){
        events.add(newEvent);
    }
    public void deleteEvent(Event deleteEvent){
        events.remove(deleteEvent);
    }
    public boolean isEventInList(Event eventSearch){
        for (Event i :
                getEvents()) {
            if (i.equals(eventSearch) ) {
                return true;
            }
        }
        return false;
    }
    public void printEventsForOrganizer(User organizer) {
        int index = 1;

        for (Event i:
             events) {
            if (i.organizer == organizer) {
                LOGGER.log(Level.INFO, "Event No.{0}", index);
                i.printEventDetails();
            }
            index++;
        }
    }
    public void printEvents() {
        int index = 1;
        for (Event i:
                events) {
            LOGGER.log(Level.INFO, "Event No.{0}", index);
                i.printEventDetails();
            index++;
        }
    }
    public boolean isOrganizerOfEvent(User organizer){
        for (Event i :
                events) {
            if (i.organizer.equals(organizer)) {
                return true;
            }
            }
        return false;
    }
    public void displayEventsForOrganizer(User currentUser) {
        int index = 1;
        for (Event i:
                events) {
            if (i.organizer.equals(currentUser)){
                printEventNum(index);
                i.printEventDetails();
            }
            index++;
        }
    }
    public void printEventsReports(User currentUser) {
        int index = 1;
        for (Event i:
                events) {
            if (i.organizer.equals(currentUser)){
                printEventNum(index);
                i.printReport();
            }
            index++;
        }
    }
    public void displayUpComingEvents(User user) {
        int index = 1;
        for (Event i:
                events) {
            if (i.guestList.contains(user) && CalendarUtil.eventDateType(i.eventDate) != DATED){
                printEventNum(index);
                i.printEventDetails();
            }
            index++;
        }
    }
    public void displayEventsWithin2Days(User user) {
        int index = 1;
        for (Event i:
                events) {
            if (i.guestList.contains(user) && CalendarUtil.eventDateType(i.eventDate) == WITHIN_2_DAYS){
                printEventNum(index);
                i.printEventDetails();
            }
            index++;
        }
    }
    public static void printEventNum(int index){LOGGER.log(Level.INFO, "Event No.{0}", index);
    }
    public List<Event> getEvents() {
        return events;
    }
}
