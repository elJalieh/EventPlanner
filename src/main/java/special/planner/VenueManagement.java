package special.planner;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VenueManagement {
    private static final Logger LOGGER = Logger.getLogger(VenueManagement.class.getName());

    public void initializeVenues(){
        Venue v1 = new Venue("hello1", 200, "am1", 1500);
        Venue v2 = new Venue("hello2", 220, "am2", 2500);
        Venue v3 = new Venue("hello3", 250, "am3", 3500);
        venues.add(v1);
        venues.add(v2);
        venues.add(v3);
    }

    public List<Venue> venues = new ArrayList<>();
    public void addVenue(Venue newVenue){
        venues.add(newVenue);
    }

    public boolean isVenueInList(Venue venueSearch) {
        return venues.contains(venueSearch);

    }

    public void deleteVenue(Venue venueToBeDeleted) {
        this.venues.remove(venueToBeDeleted);
    }

    public void displayVenues() {
        int index = 1;

        for (Venue i:
                venues) {
            LOGGER.info("Venue no." +index +"\n" + i+"====================================================================================\n"
                    +"Venue name: " + i.venueName+"\nVenue capacity: " + i.capacity + "\t" + "Venue amenities: " + i.amenities + "\t" +
                    "Venue pricing: " + i.pricing + "\t" + "Booking status: " + i.booked);
            index++;
        }


    }
}
