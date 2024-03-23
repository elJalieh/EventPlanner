package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Event {
    //Ability for organizers to create new events specifying details such as date, time, location,
    //theme, and description, attendee count and managing guest lists
    String eventTheme;
    String eventDate;
    String eventTime;
    String eventLocation;
    String eventDescription;
    int attendeeCount;
    User organizer;
    String vendorPackages;
    Vendor eventVendor;
    Venue eventVenue;
    List<User> guestList=new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Event.class.getName());

    Event(String date, String time,String location, String theme,String description,int attendeeCount, User organizer ){
        this.eventDate = date;
        this.eventTheme = theme;
        this.eventDescription = description;
        this.eventLocation = location;
        this.eventTime = time;
        this.attendeeCount = attendeeCount;
        this.organizer = organizer;
        this.guestList.add(organizer);
        vendorPackages = "not set!";
        eventVendor = null;
        eventVenue = null;

    }
    public void addAttendee(User attendee){
        if(guestList.size() == attendeeCount) {
            LOGGER.info("Maximum number of attendees reached!");
            return;
        }
        this.guestList.add(attendee);

    }
    public void removeAttendee(User attendee){
        this.guestList.remove(attendee);
    }
    
    public void printAttendees(){
        int index = 1;
        for (User i :
                guestList) {
            LOGGER.info("Attendee No." + index + " Email: " + i.getEmail() + "\n");
        }
    }
    public void setPackage(String vendorPackages){
        this.vendorPackages = vendorPackages;
    }

    public void updateEvent(String date, String time,String location, String theme,String description,int attendeeCount){
        this.eventDate = date;
        this.eventTheme = theme;
        this.eventDescription = description;
        this.eventLocation = location;
        this.eventTime = time;
        this.attendeeCount = attendeeCount;
    }

    public void printEventDetails(){
        String vendorName;
        if(this.eventVendor == null){
            vendorName = "not set!";
        }
        else{
            vendorName = this.eventVendor.email;
        }
        LOGGER.info("====================================================================================\n"
                +"Event organizer: " + this.organizer.getEmail()+"\nEvent theme: " + this.eventTheme + "\t" + "Event description: " + this.eventDescription + "\t" +
                "Event Date: " + this.eventDate + "\n" + "Event Time: " + this.eventTime + "\t Associated Vendor: " + vendorName +"\t Associated Package: " + this.vendorPackages + "\n");
    }

    public boolean isNotTheOrganizerOfTheEvent(User org){
        return !Objects.equals(this.organizer, org);
    }
    public void setVendor(Vendor selectedVendor) {
        if (this.eventVendor != null){
            LOGGER.info("Vendor already booked!");
        }
        else if (this.organizer.budget >= selectedVendor.pricing) {
            this.eventVendor = selectedVendor;
            this.organizer.budget -= selectedVendor.pricing;
            LOGGER.info("Vendor associated successfully!");

        }
        else{
            LOGGER.info("Not enough budget!");
        }
    }
    public boolean hasVenue(){
        return this.eventVenue != null;
    }

    public void setAssociatedVenue(Venue venueToBeAssociated) {
        if (this.eventVenue != null){
            LOGGER.info("Venue already booked!");
        }
        else if (this.organizer.budget >= venueToBeAssociated.pricing) {
            this.eventVenue = venueToBeAssociated;
            this.organizer.budget -= venueToBeAssociated.pricing;
            LOGGER.info("Venue associated successfully!");
        }
        else{
            LOGGER.info("Not enough budget!");
        }
    }

    public void releaseVendor( ){
        this.eventVendor = null;
    }

    public void releaseVenue(){
        this.eventVenue = null;
    }




    public void printReport(){
        int total;
        String vendorPricing;
        String venuePricing;
        if (!this.hasVendor() && this.hasVenue()){
            venuePricing = String.valueOf(this.eventVenue.pricing);
            vendorPricing = "N/A";
             total = this.eventVenue.pricing;
        } else if (this.hasVendor() && !this.hasVenue()) {
            venuePricing = "N/A";
            vendorPricing = String.valueOf(this.eventVendor.pricing);
            total = this.eventVendor.pricing;
        } else if (this.hasVendor() && this.hasVenue()) {
            venuePricing = String.valueOf(this.eventVenue.pricing);
            vendorPricing = String.valueOf(this.eventVendor.pricing);
            total = this.eventVendor.pricing + this.eventVenue.pricing;
        }
        else {
            venuePricing = "N/A";
            vendorPricing = "N/A";
            total = 0;
        }
        LOGGER.info("Vendor payment : \t" + vendorPricing + "\n"+
                    "Venue payment : \t" + venuePricing + "\n"+
                    "=========================================================\n" +
                    "Event Total : \t" + total + "\tRemaining budget: \t" + this.organizer.budget+"\n"
                );

    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public boolean hasVendor() {
        return this.eventVendor != null;
    }
}
