package special.planner;

import java.util.logging.Logger;

public class Venue {
    String venueName;
    int capacity;
    String amenities;
    int pricing;
    Event associatedEvent;
    boolean booked;
    private static final Logger LOGGER = Logger.getLogger(Venue.class.getName());
    Venue(String venueName, int capacity, String amenities, int pricing){
        this.venueName = venueName;
        this.amenities = amenities;
        this.capacity = capacity;
        this.pricing = pricing;
        this.booked = false;
    }
    public void editVenue(String venueName, int capacity, String amenities, int pricing){
        this.venueName = venueName;
        this.amenities = amenities;
        this.capacity = capacity;
        this.pricing = pricing;
    }
    public void setAssociatedEvent(Event associatedEvent){
        if(!booked) {
            booked = true;
            this.associatedEvent = associatedEvent;
        }
        else  LOGGER.info("This venue is booked!");
    }
    public Event getAssociatedEvent(){
        return this.associatedEvent;
    }
}
