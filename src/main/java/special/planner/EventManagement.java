package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EventManagement {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public List<Event> Events = new ArrayList<>();
    public void initializer(User currentUser){
        Event e1 = new Event("ee1", "sd", "hello", "hello", "hello", 30, currentUser);
        Event e2 = new Event("ee2", "hedfgsdllo", "hello", "hello", "hello", 50, currentUser);
        Event e3 = new Event("ee3", "hedfgsdllo", "hello", "hello", "hello", 50, currentUser);

    }
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

    public void printEvents() {
        int index = 1;

        for (Event i:
             Events) {
            LOGGER.info("event no." +index +"\n" + i+"====================================================================================\n"
                    +"event organizer: " + i.Organizer.getEmail()+"\nevent theme: " + i.EventTheme + "\t" + "event description: " + i.EventDescription + "\t" +
                    "event Date: " + i.EventDate + "\n" + "event Time: " + i.EventTime);
            index++;
        }
    }
}
