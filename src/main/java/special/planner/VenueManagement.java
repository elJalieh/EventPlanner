package special.planner;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
    List<Venue> venues = new ArrayList<>();
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
            LOGGER.log(Level.INFO,
                    """
                            =======================================================
                            Venue No. {0}
                            Venue Name: {1}
                            Venue Capacity: {2}
                            Venue Amenities: {3}
                            Venue Pricing: {4}
                            Booking Status: {5}""",
                    new Object[]{index++, i.venueName, i.capacity, i.amenities, i.pricing, i.booked});
        }
    }
    public List<Venue> getVenues() {
        return venues;
    }
}
