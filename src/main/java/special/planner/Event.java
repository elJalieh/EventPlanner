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
    String Package;
    Vendor eventVendor;
    Venue eventVenue;
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
        Package = "not set!";
        eventVendor = null;
        eventVenue = null;

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
    public void setPackage(String Package){
        this.Package = Package;
    }

    public void updateEvent(String Date, String Time,String Location, String Theme,String Description,int AttendeeCount){
        this.EventDate = Date;
        this.EventTheme = Theme;
        this.EventDescription = Description;
        this.EventLocation = Location;
        this.EventTime = Time;
        this.AttendeeCount = AttendeeCount;
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
                +"event organizer: " + this.Organizer.getEmail()+"\nevent theme: " + this.EventTheme + "\t" + "event description: " + this.EventDescription + "\t" +
                "event Date: " + this.EventDate + "\n" + "event Time: " + this.EventTime + "\t Associated Vendor: " + vendorName +"\t Associated Package: " + this.Package + "\n");
    }


    public void setVendor(Vendor selectedVendor) {
        if (this.eventVendor != null){
            LOGGER.info("vendor already booked!");
            return;
        }
        else if (this.Organizer.budget >= selectedVendor.Pricing) {
            this.eventVendor = selectedVendor;
            this.Organizer.budget -= selectedVendor.Pricing;
            LOGGER.info("vendor associated successfully!");

        }
        else{
            LOGGER.info("Not enough budget!");
        }
    }

    public void setAssociatedVenue(Venue venueToBeAssociated) {
        if (this.eventVenue != null){
            LOGGER.info("venue already booked!");
        }
        else if (this.Organizer.budget >= venueToBeAssociated.pricing) {
            this.eventVenue = venueToBeAssociated;
            this.Organizer.budget -= venueToBeAssociated.pricing;
            LOGGER.info("venue associated successfully!");
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
        int total = this.eventVendor.Pricing + this.eventVenue.pricing;

        LOGGER.info("Vendor payment : \t" + this.eventVendor.Pricing + "\n"+
                    "Venue payment : \t" + this.eventVenue.pricing + "\n"+
                    "=========================================================\n" +
                    "Event Total : \t" + total + "\tremaining budget: \t" + this.Organizer.budget+"\n"
                );

    }

    public void setOrganizer(User Orginizer) {
        this.Organizer = Orginizer;
    }
}
