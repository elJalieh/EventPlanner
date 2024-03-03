package special.planner;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VenueManagement {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public void initializeVenues(){
        Venue v1 = new Venue("hello1", 200, "am1", 1500);
        Venue v2 = new Venue("hello2", 220, "am2", 2500);
        Venue v3 = new Venue("hello3", 250, "am3", 3500);
        Venues.add(v1);
        Venues.add(v2);
        Venues.add(v3);
    }

    public List<Venue> Venues = new ArrayList<>();
    public void addVenue(Venue newVenue){
        Venues.add(newVenue);
    }

    public boolean isVenueInList(Venue venueSearch) {
        return Venues.contains(venueSearch);

    }

    public void deleteVenue(Venue venueToBeDeleted) {
        this.Venues.remove(venueToBeDeleted);
    }

    public void displayVenues() {
        int index = 1;

        for (Venue i:
                Venues) {
            LOGGER.info("venue no." +index +"\n" + i+"====================================================================================\n"
                    +"venue name: " + i.venueName+"\nvenue capacity: " + i.capacity + "\t" + "venue amenities: " + i.amenities + "\t" +
                    "venue pricing: " + i.pricing);
            index++;
        }


    }
}
