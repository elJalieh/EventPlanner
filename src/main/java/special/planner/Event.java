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
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    Event(String Date, String Time,String Location, String Theme,String Description,int AttendeeCount, User Organizer ){
        this.eventDate = Date;
        this.eventTheme = Theme;
        this.eventDescription = Description;
        this.eventLocation = Location;
        this.eventTime = Time;
        this.attendeeCount = AttendeeCount;
        this.organizer = Organizer;
        this.guestList.add(Organizer);
        vendorPackages = "not set!";
        eventVendor = null;
        eventVenue = null;

    }
    public void addAttendee(User Attendee){
        if(guestList.size() == attendeeCount) {
            logger.info("maximum number of attendees reached!");
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
            logger.info("Attendee No." + index
            + " email: " + i.getEmail() + "\n");
        }
    }
    public void setPackage(String Package){
        this.vendorPackages = Package;
    }

    public void updateEvent(String Date, String Time,String Location, String Theme,String Description,int AttendeeCount){
        this.eventDate = Date;
        this.eventTheme = Theme;
        this.eventDescription = Description;
        this.eventLocation = Location;
        this.eventTime = Time;
        this.attendeeCount = AttendeeCount;
    }

    public void printEventDetails(){
        String vendorName;
        if(this.eventVendor == null){
            vendorName = "not set!";
        }
        else{
            vendorName = this.eventVendor.email;
        }
        logger.info("====================================================================================\n"
                +"event organizer: " + this.organizer.getEmail()+"\nevent theme: " + this.eventTheme + "\t" + "event description: " + this.eventDescription + "\t" +
                "event Date: " + this.eventDate + "\n" + "event Time: " + this.eventTime + "\t Associated Vendor: " + vendorName +"\t Associated Package: " + this.vendorPackages + "\n");
    }

    public boolean isTheOrganizerOfTheEvent(User org){
        return Objects.equals(this.organizer, org);
    }
    public void setVendor(Vendor selectedVendor) {
        if (this.eventVendor != null){
            logger.info("vendor already booked!");
        }
        else if (this.organizer.budget >= selectedVendor.pricing) {
            this.eventVendor = selectedVendor;
            this.organizer.budget -= selectedVendor.pricing;
            logger.info("vendor associated successfully!");

        }
        else{
            logger.info("Not enough budget!");
        }
    }
    public boolean hasVenue(){
        return this.eventVenue != null;
    }

    public void setAssociatedVenue(Venue venueToBeAssociated) {
        if (this.eventVenue != null){
            logger.info("venue already booked!");
        }
        else if (this.organizer.budget >= venueToBeAssociated.pricing) {
            this.eventVenue = venueToBeAssociated;
            this.organizer.budget -= venueToBeAssociated.pricing;
            logger.info("venue associated successfully!");
        }
        else{
            logger.info("Not enough budget!");
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
        logger.info("Vendor payment : \t" + vendorPricing + "\n"+
                    "Venue payment : \t" + venuePricing + "\n"+
                    "=========================================================\n" +
                    "Event Total : \t" + total + "\tremaining budget: \t" + this.organizer.budget+"\n"
                );

    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public boolean hasVendor() {
        return this.eventVendor != null;
    }
}
