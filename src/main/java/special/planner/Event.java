package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Event {
    //Ability for organizers to create new events specifying details such as date, time, location,
    //theme, and description, attendee count and managing guest lists
    String EventTheme;
    String EventDate;
    String EventTime;
    String EventLocation;
    String EventDescription;
    int AttendeeCount;
    User Organizer;
     int id = 0;
    List<User> guestList=new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    Event(String Date, String Time,String Location, String Theme,String Description,int AttendeeCount, User Organizer ){
        this.EventDate = Date;
        this.EventTheme = Theme;
        this.EventDescription = Description;
        this.EventLocation = Location;
        this.EventTime = Time;
        this.AttendeeCount = AttendeeCount;
        this.Organizer = Organizer;

    }
    public void addAttendee(User Attendee){
        if(guestList.size() == AttendeeCount) {
            LOGGER.info("maximum number of attendees reached!");
            return;
        }
        this.guestList.add(Attendee);

    }
    public void removeAttendee(User Attendee){
        this.guestList.remove(Attendee);
    }
    
    public void printAttendees(){
        int index = 1;
        for (User i :
                guestList) {
            LOGGER.info("Attendee No." + index
            + " email: " + i.getEmail() + "\n");
        }
    }

    public void updateEvent(String Date, String Time,String Location, String Theme,String Description,int AttendeeCount){
        this.EventDate = Date;
        this.EventTheme = Theme;
        this.EventDescription = Description;
        this.EventLocation = Location;
        this.EventTime = Time;
        this.AttendeeCount = AttendeeCount;
    }


}
