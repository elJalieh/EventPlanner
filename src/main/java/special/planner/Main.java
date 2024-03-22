package special.planner;
import java.util.Objects;
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
    private static final String INVALID_CHOICE_MESSAGE = "Invalid choice! Please try again.";
    public static final int USER_TYPE = 1;
    public static int whichType = 0;
    public static final int VENDOR_TYPE = 2;
    public static final int NOT_VALID = 0;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        login.initializeUsers();
        eventManager = new EventManagement();
        venueManager = new VenueManagement();
        venueManager.initializeVenues();// will be removed later


        manageUserRegistration();
    }

    public static void manageUserRegistration() {
        login.setLogInStatus(false);
        boolean exitLoop = false;
        while (!exitLoop) {
            LOGGER.info("""
                    Enter your choice:
                    1. Sign Up
                    2. Login
                    3. Exit""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> signUp();
                case 2 -> signIn();
                case 3 -> exitLoop = true;
                default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
            }
            if (login.isLoggedIn()) {
                if(whichType == USER_TYPE) {
                    switch (currentUser.getType()) {
                        case ADMIN -> adminScreen();
                        case USER -> userScreen();
                        default -> manageUserRegistration();
                    }
                } else if (whichType == VENDOR_TYPE) {
                    serviceProviderScreen();
                } else manageUserRegistration();
            }
        }
    }

    private static void serviceProviderScreen() {
        LOGGER.info("Service Provider Screen");
        LOGGER.info("""
                please enter your choice
                1. Show packages
                2. add a new package
                3. edit a package
                4. delete a package
                5. edit/add contract
                6. show costumer details
                7. Logout
                8. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1 -> showPackages();
            case 2 -> addPackage();
            case 3 -> alterPackage("edit");
            case 4 -> alterPackage("delete");
            case 5 -> editAddContract();
            case 6 -> showCostumerDetails();
            case 7 -> manageUserRegistration();
            case 8 -> System.exit(0);
            default -> LOGGER.info("Invalid choice!");
        }
        serviceProviderScreen();


    }

    private static void showCostumerDetails() {
        if(currentVendor.vendorEvent == null){
            LOGGER.info("you're not associated with an event!");
            serviceProviderScreen();
        }

        currentVendor.vendorEvent.printEventDetails();
        serviceProviderScreen();

    }

    private static void editAddContract() {
        LOGGER.info("this is your current contract.\n");
        LOGGER.info(currentVendor.contractDescription);
        LOGGER.info("Enter your new contract: ");
        String newContract = scanner.nextLine();
        LOGGER.info("are you sure you want to edit? y/n\n");
        if (!scanner.nextLine().equalsIgnoreCase("y")){
            LOGGER.info("edit failed!");
            serviceProviderScreen();
        }
        currentVendor.setContractDescription(newContract);
        LOGGER.info("contract edited/added successfully!");
        serviceProviderScreen();
    }

    private static void alterPackage(String alterationType) {
        if(currentVendor.Packages.isEmpty()){
            LOGGER.info("Add a package first to " + alterationType);
            serviceProviderScreen();
        }
        currentVendor.displayPackages();
        LOGGER.info("Enter package number you want to" + alterationType);
        int packageNo = scanner.nextInt();
        scanner.nextLine();
        if (packageNo > currentVendor.Packages.size()) {
            numberDoesntExistMessage();
            serviceProviderScreen();
        }
        if(alterationType.equalsIgnoreCase("delete")) {
            currentVendor.Packages.remove(packageNo - 1);
        } else if (alterationType.equalsIgnoreCase("edit")) {
            LOGGER.info("Enter edit:");
            String editedPackage = scanner.nextLine();
            currentVendor.Packages.set(packageNo-1, editedPackage);
        }
        LOGGER.info("Package " + alterationType +"ed successfully!");
        serviceProviderScreen();
    }

    private static void addPackage() {
        LOGGER.info("enter package you want to add:\n");
        String newPackage = scanner.nextLine();
        currentVendor.addPackage(newPackage);
        LOGGER.info("package added successfully!");
        serviceProviderScreen();
    }

    private static void showPackages() {
        currentVendor.displayPackages();
        serviceProviderScreen();
    }

    private static void userScreen() {
        LOGGER.info("User Screen");
        LOGGER.info("""
                Please enter your choice
                1. Manage events
                2. Register in an event
                3. Logout
                4. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageEvents();
            case 2 -> registerInEvent();
            case 3 -> manageUserRegistration();
            case 4 -> System.exit(0);
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
        userScreen();
    }

    private static void bookVenue() {
        eventManager.printEventsForOrganizer(currentUser);
        LOGGER.info("Enter event number to book a venue for: ");
        Event pickedEvent = selectEvent();
        if(!pickedEvent.isTheOrganizerOfTheEvent(currentUser)){
            printNotOrganizer();
            manageEvents();
        }
        if (pickedEvent.hasVenue()){
            LOGGER.info("the event is already associated with a venue!");
            manageEvents();
        }
        LOGGER.info("your current budget is: " + currentUser.budget);
        printVenues();
        LOGGER.info("Enter venue number to book: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if (venueNo > venueManager.Venues.size()) {
            numberDoesntExistMessage();
            userScreen();
        }
        Venue pickedVenue = venueManager.Venues.get(venueNo-1);
        if (pickedVenue.booked){
            LOGGER.info("venue is booked!");
            bookVenue();
        }
        if(pickedVenue.pricing > currentUser.budget){
            LOGGER.info("can't afford venue!");
            manageEvents();

        }
        pickedVenue.setAssociatedEvent(pickedEvent);
        pickedEvent.setAssociatedVenue(pickedVenue);
        manageEvents();
    }

    private static void registerInEvent() {
        eventManager.printEvents();
        LOGGER.info("Enter event number to register: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if (eventNo > eventManager.Events.size()) {
            numberDoesntExistMessage();
            userScreen();
        }
        eventManager.Events.get(eventNo - 1).addAttendee(currentUser);
        LOGGER.info("Registration successful!");
        userScreen();


    }

    private static void manageEvents() {
        LOGGER.info("""
                enter your choice of management:
                1. create event
                2. edit event
                3. delete event
                4. display all of the events
                5. book a venue for a specified event.
                6. display attendees of an event
                7. filter and assign vendors to an event
                8. release vendor of an event
                9. release venue of an event
                10. add budget
                11. print budget report
                12. go back to user screen
                13. see upcoming events
                14. reminders and notifications for near events
                15. exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> createEvent();
            case 2 -> editEvent();
            case 3 -> deleteEvent();
            case 4 -> eventManager.printEvents();
            case 5 -> bookVenue();
            case 6 -> displayAttendees();
            case 7 -> assignVendors();
            case 8 -> releaseResource("vendor");
            case 9 -> releaseResource("venue");
            case 10 -> addBudget();
            case 11 -> printBudgetReport();
            case 12 -> userScreen();
            case 13 -> displayUpComingEvents();
            case 14 -> displayNearEvents();
            case 15 -> System.exit(0);

            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
        userScreen();
    }

    private static void displayNearEvents() {
        eventManager.displayEventsWithin2Days(currentUser);
    }

    private static void displayUpComingEvents() {
        eventManager.displayUpComingEvents(currentUser);
    }

    private static void printBudgetReport() {
        eventManager.printEventsReports(currentUser);
        manageEvents();
    }

    private static void releaseResource(String resourceName) {
        eventManager.printEventsForOrganizer(currentUser);
        LOGGER.info("Enter event number to release the " + resourceName + " for: ");
        Event pickedEvent = selectEvent();
        if (!pickedEvent.isTheOrganizerOfTheEvent(currentUser)) {
            printNotOrganizer();
            manageEvents();
        }
        if (resourceName.equals("venue") && pickedEvent.hasVenue()) {
            pickedEvent.releaseVenue();
            LOGGER.info("The " + resourceName + " was released successfully!");
        } else if (resourceName.equals("vendor") && pickedEvent.hasVenue()) {
            pickedEvent.eventVendor.releaseEvent();
            pickedEvent.releaseVendor();
            LOGGER.info("The " + resourceName + " was released successfully!");
        }
        manageEvents();
    }


    private static void addBudget() {
        LOGGER.info("Enter your budget\n");
        int budget = scanner.nextInt();
        scanner.nextLine();
        currentUser.setBudget(budget);
    }
    private static Event selectEvent() {
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if (eventNo > eventManager.Events.size()) {
            LOGGER.info("Event number does not exist!");
            userScreen();
        }
        return eventManager.Events.get(eventNo - 1);
    }

    private static void isOrganizerEventAndHasVendor(Event associatedEvent){
        if (!associatedEvent.isTheOrganizerOfTheEvent(currentUser)){
            printNotOrganizer();
            assignVendors();
        }
        if(associatedEvent.hasVendor()){
            LOGGER.info("this event already has a vendor!");
            manageEvents();
        }
    }
    private static void assignVendors() {
        if (!eventManager.isOrganizerOfEvent(currentUser)) {
            LOGGER.info("you're not an organizer of an event!\n");
            manageEvents();
        }
        eventManager.displayEventsForOrganizer(currentUser);
        LOGGER.info("Enter the event number you want to associate the vendor with\n");

        Event associatedEvent = selectEvent();
        isOrganizerEventAndHasVendor(associatedEvent);
        do {
            LOGGER.info("""
                    select how to filter vendors:\s

                    1. display all vendors
                    2. display vendors by availability
                    3. display vendors by pricing
                    4. display vendors by location
                    5. display vendors by reviews
                    6. go back to user screen
                    7. exit""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            LOGGER.info("your current budget is: " + currentUser.budget);
            switch (choice) {
                case 1 -> login.displayAllVendors();
                case 2 -> login.displayVendorByAvailability(true);
                case 3 -> displayByPricing();
                case 4 -> displayByLocation();
                case 5 -> displayByReviews();
                case 6 -> userScreen();
                case 7 -> System.exit(0);
                default -> assignVendors();
            }
            LOGGER.info("want to try and filter again? y/n");
        } while (scanner.nextLine().equalsIgnoreCase("y"));
        LOGGER.info("enter vendor you want to contact");
        int vendorNo = scanner.nextInt();
        scanner.nextLine();
        if (vendorNo > login.getNumberOfVendors()) {
            LOGGER.info("vendor number does not exist!");
            userScreen();
        }
        Vendor selectedVendor = login.vendors.get(vendorNo - 1);
        if (!selectedVendor.Availability){
            LOGGER.info("vendor is not available!");
            assignVendors();
        }
        LOGGER.info(selectedVendor.getContractDescription() + "\n");
        LOGGER.info("accept contract? y/n\n");
        String YN = scanner.nextLine();
        if (YN.equalsIgnoreCase("n") || (currentUser.budget < selectedVendor.Pricing)) {
            LOGGER.info("contract failed!");
            userScreen();
        } else if (YN.equalsIgnoreCase("Y")) {
            currentUser.linkWithVendor(selectedVendor);
            LOGGER.info("linked with vendor successfully!\n");
            LOGGER.info("enter number of a package:\n");
            selectedVendor.displayPackages();
            int packageChoice = getPackageFromVendor(selectedVendor);
            associatedEvent.setPackage(selectedVendor.getPackageName(packageChoice - 1, currentUser));
            associatedEvent.setVendor(selectedVendor);
            selectedVendor.setEvent(associatedEvent);
            LOGGER.info("package is added to event successfully!\n");
        }
        manageEvents();

    }

    private static int getPackageFromVendor(Vendor selectedVendor){
        int packageChoice;
        while (true) {
            packageChoice = scanner.nextInt();
            scanner.nextLine();
            if (packageChoice > selectedVendor.Packages.size()) {
                LOGGER.info("package number does not exist!, try again.");
                continue;
            }
            break;
        }
        return packageChoice;
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
        LOGGER.info("Enter event number: ");
        selectEvent().printAttendees();
    }


    private static void deleteEvent() {
        eventManager.printEventsForOrganizer(currentUser);
        LOGGER.info("Enter event number: ");
        Event pickedEvent = selectEvent();
        if (!pickedEvent.isTheOrganizerOfTheEvent(currentUser)){
            printNotOrganizer();
            manageEvents();
        }
        pickedEvent.eventVendor.releaseEvent();
        eventManager.deleteEvent(pickedEvent);
        LOGGER.info("Event deleted successfully!\n");
        manageEvents();
    }
    private static void numberDoesntExistMessage(){
        LOGGER.info("Number does not exist!");
    }
    private static void editEvent() {
        eventManager.printEventsForOrganizer(currentUser);
        LOGGER.info("Enter event number: ");
        Event pickedEvent = selectEvent();
        if (!pickedEvent.isTheOrganizerOfTheEvent(currentUser)){
            printNotOrganizer();
            editEvent();
        }
        LOGGER.info("Enter the Date: ");
        String date = scanner.nextLine();

        LOGGER.info("Enter the Time: ");
        String time = scanner.nextLine();

        LOGGER.info("Enter the Location: ");
        String location = scanner.nextLine();

        LOGGER.info("Enter the Theme: ");
        String theme = scanner.nextLine();

        LOGGER.info("Enter the Description: ");
        String description = scanner.nextLine();

        LOGGER.info("Enter the Attendee Count: ");
        String attendeeCountString = scanner.nextLine();
        int attendeeCount = Integer.parseInt(attendeeCountString);
        pickedEvent.updateEvent(date, time, location, theme, description, attendeeCount);
        LOGGER.info("Event updated successfully!\n");
        manageEvents();
    }

    private static void createEvent() {
        //String Date, String Time,String Location, String Theme,String Description,int AttendeeCount, User Organizer
        LOGGER.info("Enter the Date:");
        String date = scanner.nextLine();

        LOGGER.info("Enter the Time: ");
        String time = scanner.nextLine();

        LOGGER.info("Enter the Location: ");
        String location = scanner.nextLine();

        LOGGER.info("Enter the Theme: ");
        String theme = scanner.nextLine();

        LOGGER.info("Enter the Description: ");
        String description = scanner.nextLine();

        LOGGER.info("Enter the Attendee Count: ");
        String attendeeCountString = scanner.nextLine();
        int attendeeCount = Integer.parseInt(attendeeCountString);
        Event newEvent = new Event(date, time, location, theme, description, attendeeCount, currentUser);
        eventManager.addEvent(newEvent);
//        eventManager.addEvent(new Event(date, time, location, theme, description, attendeeCount, currentUser));
//        eventManager.addEvent(new Event(date, time, location, theme, description, attendeeCount, currentUser));
        LOGGER.info("Event added successfully!\n");
        manageEvents();


    }

    private static void adminScreen() {
        LOGGER.info("Admin screen");
        LOGGER.info("""
                Please enter your choice
                1. Manage venues
                2. Delete account
                3. Create account for "Service Provider"
                4. Logout
                5. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageVenues();
            case 2 -> deleteAccount();
            case 3 -> addServiceProviderAccount();
            case 4 -> manageUserRegistration();
            case 5 -> System.exit(0);
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
    }

    private static void deleteAccount() {
        String emailToDelete;
        LOGGER.info("Enter email to delete:");
        emailToDelete = scanner.nextLine();
        if(Objects.equals(currentUser.getEmail(), emailToDelete)){
            LOGGER.info("Deleting your own account...");
            login.deleteUser(emailToDelete);
            manageUserRegistration();
        }
        login.deleteUser(emailToDelete);
        adminScreen();
    }

    private static void addServiceProviderAccount() {
        String serviceProviderEmail;
        String serviceProviderPassword;
        String serviceProviderCategory;
        String serviceProviderLocation;
        int serviceProviderPricing;
        int serviceProviderReview;
        LOGGER.info("Enter Service Provider Email:");
        serviceProviderEmail = scanner.nextLine();
        LOGGER.info("Enter Password:");
        serviceProviderPassword = scanner.nextLine();
        LOGGER.info("Enter Service Provider Category:");
        serviceProviderCategory = scanner.nextLine();
        LOGGER.info("Enter Service Provider Location:");
        serviceProviderLocation = scanner.nextLine();
        LOGGER.info("Enter Service Provider Pricing:");
        serviceProviderPricing = scanner.nextInt();
        scanner.nextLine();
        LOGGER.info("Enter Service Provider Review:");
        serviceProviderReview = scanner.nextInt();
        scanner.nextLine();
        login.addServiceProvider(serviceProviderEmail, serviceProviderPassword, serviceProviderCategory, serviceProviderLocation, serviceProviderPricing , serviceProviderReview, null);
        adminScreen();
    }

    private static void manageVenues() {
        LOGGER.info("""
                Enter your choice of management:
                1. Add venue
                2. Edit venue
                3. Delete venue
                4. Display the venues
                5. Display booked event
                6. Go back to user screen
                7. Exit""");
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

            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
        manageVenues();
    }

    private static void displayBookedEvent() {
        LOGGER.info("Enter venue number: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if (venueNo > venueManager.Venues.size()) {
            numberDoesntExistMessage();
            manageVenues();
        }
        if (venueManager.Venues.get(venueNo - 1).associatedEvent != null)
            venueManager.Venues.get(venueNo - 1).associatedEvent.printEventDetails();
        else {
            LOGGER.info("Venue is not booked!");
        }
        manageVenues();

    }

    private static void printVenues() {
        venueManager.displayVenues();
    }

    private static void deleteVenue() {
        LOGGER.info("Enter venue number to delete: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if (venueNo > venueManager.Venues.size()) {
            numberDoesntExistMessage();
            manageVenues();
        }
        venueManager.deleteVenue(venueManager.Venues.get(venueNo - 1));
        LOGGER.info("Venue deleted successfully!");

    }

    private static void editVenue() {
        LOGGER.info("Enter venue number: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if (venueNo > venueManager.Venues.size()) {
            numberDoesntExistMessage();
            editVenue();
        }

        LOGGER.info("Enter the updated Name: ");
        String Name = scanner.nextLine();

        LOGGER.info("Enter the updated Capacity: ");
        String capacityStr = scanner.nextLine();
        int capacity = Integer.parseInt(capacityStr);
        LOGGER.info("Enter the updated amenities: ");
        String amenities = scanner.nextLine();

        LOGGER.info("Enter the updated pricing: ");
        String pricingStr = scanner.nextLine();
        int pricing = Integer.parseInt(pricingStr);

        venueManager.Venues.get(venueNo - 1).editVenue(Name, capacity, amenities, pricing);
        LOGGER.info("Venue updated successfully!\n");
        manageVenues();
    }

    private static void addVenue() {
        //String venueName, int capacity, String amenities, int pricing
        LOGGER.info("Enter the Name: ");
        String Name = scanner.nextLine();

        LOGGER.info("Enter the Capacity: ");
        String capacityStr = scanner.nextLine();
        int capacity = Integer.parseInt(capacityStr);
        LOGGER.info("Enter the amenities: ");
        String amenities = scanner.nextLine();

        LOGGER.info("Enter the pricing: ");
        String pricingStr = scanner.nextLine();
        int pricing = Integer.parseInt(pricingStr);

        venueManager.addVenue(new Venue(Name, capacity, amenities, pricing));
        LOGGER.info("Venue added successfully!\n");
        manageVenues();
    }

    private static void signUp() {
        LOGGER.info("Enter your email: ");
        String email = scanner.nextLine();
        if (login.emailExists(email)) {
            LOGGER.info("Email exists");
            signUp();
        }
        LOGGER.info("Enter your password: ");
        String password = scanner.nextLine();

        login.addUser(email, password);

    }

    private static void signIn() {
        whichType = NOT_VALID;
        LOGGER.info("Enter email: ");

        String email = scanner.nextLine();

        LOGGER.info("Enter password: ");
        String password = scanner.nextLine();
        whichType = login.isValid(email, password);
        if (whichType == NOT_VALID) {
            LOGGER.info("Not welcome!");
            manageUserRegistration();
        } else if (whichType == USER_TYPE) {
            currentUser = login.getCurrentUser(email, password);
            currentVendor = null;
            login.setLogInStatus(true);
        } else if (whichType == VENDOR_TYPE) {
            currentVendor = login.getCurrentVendor(email, password);
            currentUser = null;
            login.setLogInStatus(true);

        } else {
            LOGGER.info("Error signing in!");
            manageUserRegistration();
        }
    }
    private static void printNotOrganizer(){
        LOGGER.info("You're not the organizer of the event!");
    }
}
