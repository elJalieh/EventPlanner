package special.planner;

import java.util.logging.Logger;

//| Venue Name       | Capacity              | Amenities                               | Pricing     |
//      | Grand Banquet Hall | 100      | Parking, Wi-Fi, Catering, AV Equipment | $5000/day   |
public class Venue {
    String venueName;
    int capacity;
    String amenities;
    int pricing;
    Event associatedEvent;
    boolean booked;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

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
        else  logger.info("this venue is booked!");

    }

    public Event getAssociatedEvent(){
        return this.associatedEvent;
    }

}
