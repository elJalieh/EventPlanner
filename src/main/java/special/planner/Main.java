package special.planner;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    static User currentUser;
    static Vendor currentVendor;
    static EventManagement eventManager;
    static VenueManagement venueManager;
    static Scanner scanner = new Scanner(System.in);
   static Login login = new Login();
    public static final String ADMIN = "Admin";
    public static final String USER = "User";
    public static final int USER_TYPE = 1;
    public static final int VENDOR_TYPE = 2;
    public static final String SERVICE_PROVIDER = "Service Provider";
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        eventManager = new EventManagement();
        venueManager = new VenueManagement();
        venueManager.initializeVenues();// will be removed later


        manageUserRegistration();
    }

    public static void manageUserRegistration(){
        while(true){
            LOGGER.info("enter your choice:");
            LOGGER.info("1. Sign Up\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> signUp();
                case 2 -> signIn();
                case 3 -> System.exit(0);
                default -> LOGGER.info("Invalid choice! Please try again.");
            }
            if (login.logInStatus) {

                switch (currentUser.getType()) {
                    case ADMIN -> adminScreen();
                    case USER -> userScreen();
                    case SERVICE_PROVIDER -> serviceProviderScreen();
                    default -> LOGGER.info("Invalid choice! Please try again.");
                }
            }
        }
    }

    private static void serviceProviderScreen() {
        LOGGER.info("sp screen");




    }

    private static void userScreen() {
        LOGGER.info("user screen\n");
        LOGGER.info("please enter your choice" +
                "\n1. manage events" +
                "\n2. register in" +
                " an event\n3. logout\n4. exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageEvents();
            case 2 -> registerInEvent();
            case 3 -> manageUserRegistration();
            case 4 -> System.exit(0);
            default -> LOGGER.info("Invalid choice! Please try again.");
        }
    }

    private static void bookVenue() {
        printVenues();
        LOGGER.info("enter venue number to book: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if(venueNo > venueManager.Venues.size()){
            LOGGER.info("number does not exist!");
            userScreen();
        }
        venueManager.Venues.get(venueNo-1).setAssociatedEvent(eventManager.Events.get(eventManager.Events.size()-1));
        eventManager.Events.get(eventManager.Events.size()-1).setAssociatedVenue(venueManager.Venues.get(venueNo-1));
    }

    private static void registerInEvent() {
        eventManager.printEvents();
        LOGGER.info("enter event number to register: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > eventManager.Events.size()){
            LOGGER.info("number does not exist!");
            registerInEvent();
        }
        eventManager.Events.get(eventNo-1).addAttendee(currentUser);
        LOGGER.info("registration successful!");
        userScreen();


    }

    private static void manageEvents() {
        LOGGER.info("enter your choice of management:" +
                "\n1. create event" +
                "\n2. edit event" +
                "\n3. delete event" +
                "\n4. display the events" +
                "\n5. display attendees of an event" +
                "\n6. filter and assign vendors to an event" +
                "\n7. go back to user screen" +
                "\n8. exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> createEvent();
            case 2 -> editEvent();
            case 3 -> deleteEvent();
            case 4 -> eventManager.printEvents();
            case 5 -> displayAttendees();
            case 6 -> assignVendors();
            case 7 -> userScreen();
            case 8 -> System.exit(0);

            default -> LOGGER.info("Invalid choice! Please try again.");
        }
        userScreen();
    }

    private static void assignVendors() {
        if(!eventManager.isOrganizerOfEvent(currentUser)){
            LOGGER.info("you're not an organizer of an event!\n");
            userScreen();
        }
        eventManager.displayEventsForOrganizer(currentUser);
        LOGGER.info("Enter the event number you want to associate the vendor with\n");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > eventManager.Events.size()){
            LOGGER.info("event number does not exist!");
            userScreen();
        }
        Event associatedEvent = eventManager.Events.get(eventNo-1);

        LOGGER.info("select how to filter vendors: \n"+
                        "\n1. display all vendors" +
                        "\n2. display vendors by availability" +
                        "\n3. display vendors by pricing" +
                        "\n4. display vendors by location" +
                        "\n5. display vendors by reviews" +
                        "\n6. go back to user screen" +
                        "\n7. exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1 -> login.displayAllVendors();
            case 2 -> login.displayVendorByAvailability(true);
            case 3 -> displayByPricing();
            case 4 -> displayByLocation();
            case 5 -> displayByReviews();
            case 6 -> userScreen();
            case 7 -> System.exit(0);
            default -> assignVendors();
        }

        int vendorNo = scanner.nextInt();
        scanner.nextLine();
        if(vendorNo > login.getNumberOfVendors()){
            LOGGER.info("vendor number does not exist!");
            userScreen();
        }
        Vendor selectedVendor = login.vendors.get(vendorNo-1);
        LOGGER.info(selectedVendor.getContractDescription()+"\n");
        LOGGER.info("accept contract? y/n\n");
        String YN = scanner.nextLine();
        if(YN.equalsIgnoreCase("n")){
            LOGGER.info("contract failed!");
            userScreen();
        }else if(YN.equalsIgnoreCase("Y")) {
            currentUser.linkWithVendor(selectedVendor);
            LOGGER.info("linked with vendor successfully!\n");
            LOGGER.info("enter number of a package:\n");
            selectedVendor.displayPackages();
            int packageChoice;
            while(true) {
                 packageChoice = scanner.nextInt();
                scanner.nextLine();
                if (packageChoice > selectedVendor.Packages.size()) {
                    LOGGER.info("package number does not exist!, try again.");
                    continue;
                }
                break;
            }
            associatedEvent.setPackage(selectedVendor.getPackageName(packageChoice-1, currentUser));
            associatedEvent.setVendor(selectedVendor);
            LOGGER.info("package is added to event successfully!\n");
        }
        manageEvents();

    }

    private static void displayByReviews() {
        LOGGER.info("Enter desired review: \n");
        int review = Integer.parseInt(scanner.nextLine());
        login.displayVendorByPrice(review);
    }

    private static void displayByLocation() {
        LOGGER.info("Enter desired location: \n");
        login.displayVendorByLocation(scanner.nextLine());
    }

    private static void displayByPricing() {
        LOGGER.info("Enter desired price: \n");
        int price = Integer.parseInt(scanner.nextLine());
        login.displayVendorByPrice(price);
    }


    private static void displayAttendees() {
        LOGGER.info("enter event number: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > eventManager.Events.size()){
            LOGGER.info("event number does not exist!");
            displayAttendees();
        }
        eventManager.Events.get(eventNo-1).printAttendees();

    }


    private static void deleteEvent() {
        LOGGER.info("enter event number: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > eventManager.Events.size()){
            LOGGER.info("event number does not exist!");
            deleteEvent();
        }
        eventManager.deleteEvent(eventManager.Events.get(eventNo-1));
        LOGGER.info("event updated successfully!\n");
        manageEvents();
    }

    private static void editEvent() {
        LOGGER.info("enter event number: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > eventManager.Events.size()){
            LOGGER.info("number does not exist!");
            editEvent();
        }
        LOGGER.info("enter the Date: ");
        String date = scanner.nextLine();

        LOGGER.info("enter the Time: ");
        String time = scanner.nextLine();

        LOGGER.info("enter the Location: ");
        String location = scanner.nextLine();

        LOGGER.info("enter the Theme: ");
        String theme = scanner.nextLine();

        LOGGER.info("enter the Description: ");
        String description = scanner.nextLine();

        LOGGER.info("enter the Attendee Count: ");
        String attendeeCountString = scanner.nextLine();
        int attendeeCount = Integer.parseInt(attendeeCountString);
        eventManager.Events.get(eventNo-1).updateEvent(date, time, location, theme, description, attendeeCount);
        LOGGER.info("event updated successfully!\n");
        manageEvents();
    }

    private static void createEvent() {
        //String Date, String Time,String Location, String Theme,String Description,int AttendeeCount, User Organizer
        LOGGER.info("enter the Date: ");
        String date = scanner.nextLine();

        LOGGER.info("enter the Time: ");
        String time = scanner.nextLine();

        LOGGER.info("enter the Location: ");
        String location = scanner.nextLine();

        LOGGER.info("enter the Theme: ");
        String theme = scanner.nextLine();

        LOGGER.info("enter the Description: ");
        String description = scanner.nextLine();

        LOGGER.info("enter the Attendee Count: ");
        String attendeeCountString = scanner.nextLine();
        int attendeeCount = Integer.parseInt(attendeeCountString);

        eventManager.addEvent(new Event(date, time, location, theme, description, attendeeCount, currentUser));
//        eventManager.addEvent(new Event(date, time, location, theme, description, attendeeCount, currentUser));
//        eventManager.addEvent(new Event(date, time, location, theme, description, attendeeCount, currentUser));
        LOGGER.info("event added successfully!\n");
        bookVenue();




    }

    private static void adminScreen() {
        LOGGER.info("admin screen\n");
        LOGGER.info("please enter your choice" +
                "\n1. manage venues" +
                "\n2. logout" +
                "\n3. create account for \"Service Provider\"" +
                "\n4. delete account"+
                "\n5. exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageVenues();
            case 2 -> manageUserRegistration();
            case 3 -> addServiceProviderAccount();
            case 4 -> deleteAccount();
            case 5 -> System.exit(0);
            default -> LOGGER.info("Invalid choice! Please try again.");
        }
    }

    private static void deleteAccount() {
        String emailToDelete;
        LOGGER.info("Enter email to delete:\n");
        emailToDelete = scanner.nextLine();
        login.deleteUser(emailToDelete);
        adminScreen();
    }

    private static void addServiceProviderAccount() {
        String serviceProviderEmail;
        String serviceProviderPassword;
        LOGGER.info("Enter Service Provider Email:\n");
        serviceProviderEmail = scanner.nextLine();
        LOGGER.info("Enter Password:\n");
        serviceProviderPassword =scanner.nextLine();
        login.addServiceProvider(serviceProviderEmail,serviceProviderPassword);
        adminScreen();
    }

    private static void manageVenues() {
        LOGGER.info("enter your choice of management:\n1. add venue\n2. edit venue\n3. delete venue\n4. display the venues\n5. display booked event\n6. go back to user screen\n7. exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> addVenue();
            case 2 -> editVenue();
            case 3 -> deleteVenue();
            case 4 -> printVenues();
            case 5 -> displayBookedEvent();
            case 6 -> userScreen();
            case 7 -> System.exit(0);

            default -> LOGGER.info("Invalid choice! Please try again.");
        }
        manageVenues();
    }

    private static void displayBookedEvent() {
        LOGGER.info("enter venue number: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if(venueNo > venueManager.Venues.size()){
            LOGGER.info("number does not exist!");
            manageVenues();
        }
        if(venueManager.Venues.get(venueNo - 1).associatedEvent != null)
             venueManager.Venues.get(venueNo-1).associatedEvent.printEventDetails();
        else{
            LOGGER.info("venue is not booked!");
        }
        manageVenues();

    }

    private static void printVenues() {
        venueManager.displayVenues();
    }

    private static void deleteVenue() {
        LOGGER.info("enter venue number to delete: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if(venueNo > venueManager.Venues.size()){
            LOGGER.info("number does not exist!");
            manageVenues();
        }
        venueManager.deleteVenue(venueManager.Venues.get(venueNo-1));
        LOGGER.info("venue deleted successfully!");

    }

    private static void editVenue() {
        LOGGER.info("enter venue number: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if(venueNo > venueManager.Venues.size()){
            LOGGER.info("number does not exist!");
            editVenue();
        }

        LOGGER.info("enter the updated Name: ");
        String Name = scanner.nextLine();

        LOGGER.info("enter the updated Capacity: ");
        String capacityStr = scanner.nextLine();
        int capacity = Integer.parseInt(capacityStr);
        LOGGER.info("enter the updated amenities: ");
        String amenities = scanner.nextLine();

        LOGGER.info("enter the updated pricing: ");
        String pricingStr = scanner.nextLine();
        int pricing = Integer.parseInt(pricingStr);

        venueManager.Venues.get(venueNo-1).editVenue(Name, capacity, amenities, pricing);
        LOGGER.info("venue updated successfully!\n");
        manageVenues();
    }

    private static void addVenue() {
        //String venueName, int capacity, String amenities, int pricing
        LOGGER.info("enter the Name: ");
        String Name = scanner.nextLine();

        LOGGER.info("enter the Capacity: ");
        String capacityStr = scanner.nextLine();
        int capacity = Integer.parseInt(capacityStr);
        LOGGER.info("enter the amenities: ");
        String amenities = scanner.nextLine();

        LOGGER.info("enter the pricing: ");
        String pricingStr = scanner.nextLine();
        int pricing = Integer.parseInt(pricingStr);

        venueManager.addVenue(new Venue(Name, capacity, amenities, pricing));
        LOGGER.info("venue added successfully!\n");
        manageVenues();

        LOGGER.info("Welcome to admin screen \n1- Create account for Service Provider \n2- Exit");



    }

    private static void signUp() {
        LOGGER.info("enter your email: ");
        String email = scanner.nextLine();
        if(login.emailExists(email)){
            LOGGER.info("email exists");
            signUp();
        }
        LOGGER.info("enter your password: ");
        String password = scanner.nextLine();

        login.addUser(email, password);

    }

    private static void signIn() {
        LOGGER.info("Enter email: ");

        String email = scanner.nextLine();

        LOGGER.info("Enter password: ");
        String password = scanner.nextLine();

        login.initializeUsers();
        int vendorOrUser = login.isValid(email, password);
        if(vendorOrUser == USER_TYPE) {
            currentUser = login.getCurrentUser(email, password);
            if (currentUser != null) {
                LOGGER.info("welcome, " + currentUser.email);
            } else LOGGER.info("not welcome");
        } else if (vendorOrUser == VENDOR_TYPE) {
            currentVendor = login.getCurrentVendor(email, password);
            if(currentVendor != null){
                LOGGER.info("welcome, "+ currentVendor.email);
            }else LOGGER.info("not welcome vendor!");


        }

    }
}