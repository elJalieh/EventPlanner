package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EventManagement {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static final int DATED = 0;
    public static final int WITHIN_2_DAYS = 1;
    public static final int WITHIN_3_OR_MORE = 2;
    public List<Event> Events = new ArrayList<>();

    public void addEvent(Event newEvent){
        Events.add(newEvent);
    }
    public void deleteEvent(Event deleteEvent){
        Events.remove(deleteEvent);
    }

    public boolean isEventInList(Event EventSearch){
        for (Event i :
                Events) {
            if (i.equals(EventSearch) ) {
                return true;
            }
        }
        return false;
    }

    public void printEventsForOrganizer(User organizer) {
        int index = 1;

        for (Event i:
             Events) {
            if (i.Organizer == organizer) {
                LOGGER.info("event no." + index + "\n");
                i.printEventDetails();
            }
            index++;
        }
    }

    public void printEvents() {
        int index = 1;

        for (Event i:
                Events) {
                LOGGER.info("event no." + index + "\n");
                i.printEventDetails();
            index++;
        }
    }
    public boolean isOrganizerOfEvent(User organizer){
        for (Event i :
                Events) {
            if (i.Organizer.equals(organizer)) {
                return true;
            }
            }
        return false;
    }

    public void displayEventsForOrganizer(User currentUser) {
        int index = 1;
        for (Event i:
                Events) {
            if (i.Organizer.equals(currentUser)){
                LOGGER.info("Event No. " + index);
                i.printEventDetails();
            }
            index++;
        }
    }

    public void printEventsReports(User currentUser) {
        int index = 1;
        for (Event i:
                Events) {
            if (i.Organizer.equals(currentUser)){
                LOGGER.info("Event No. " + index);
                i.printReport();
            }
            index++;
        }
    }

    public void displayUpComingEvents(User user) {
        int index = 1;
        for (Event i:
                Events) {
            if (i.guestList.contains(user) && CalendarUtil.eventDateType(i.EventDate) != DATED){
                LOGGER.info("Event No. " + index);
                i.printEventDetails();
            }
            index++;
        }
    }

    public void displayEventsWithin2Days(User user) {
        int index = 1;
        for (Event i:
                Events) {
            if (i.guestList.contains(user) && CalendarUtil.eventDateType(i.EventDate) == WITHIN_2_DAYS){
                LOGGER.info("Event No. " + index);
                i.printEventDetails();
            }
            index++;
        }
    }
}
