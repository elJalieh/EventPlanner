package special.planner;

import java.util.Objects;
import java.util.Scanner;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static final String NOT_ORGANIZER_MESSAGE ="You're not the organizer of the event!";
    public static final int USER_TYPE = 1;
    private static int whichType = 0;
    public static final int VENDOR_TYPE = 2;
    public static final int NOT_VALID = 0;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    static String date;
    static String time;
    static String location;
    static String theme;
    static String description;
    static int attendeeCount;
    static String amenities;
    static String name;
    static int capacity;
    static int pricing;
    public static void main(String[] args) {
        login.initializeUsers();
        eventManager = new EventManagement();
        venueManager = new VenueManagement();
        venueManager.initializeVenues();// will be removed later
        manageUserRegistration();
    }
    public static void manageUserRegistration() {
        login.setLogInStatus(false);
        int choice;
        while (true) {
            LOGGER.info("""
                    Enter your choice:
                    1. Sign Up
                    2. Login
                    3. Exit""");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1 -> signUp();
                case 2 -> signIn();
                default -> {
                    System.exit(0);
                    return;
                }
            }
            if(login.isLoggedIn()) {
                determineScreenAfterLogin();
            }
        }
    }
    private static void determineScreenAfterLogin(){
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
    private static void serviceProviderScreen() {
        LOGGER.info("Service Provider Screen");
        LOGGER.info("""
                Please enter your choice:
                1. Show Packages
                2. Add a New Package
                3. Edit a Package
                4. Delete a Package
                5. Edit/Add Contract
                6. Show Costumer Details
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
            case 8 -> {
                System.exit(0);
                return;
            }
            default -> LOGGER.info("Invalid choice!");
        }
        serviceProviderScreen();
    }
    private static void showCostumerDetails() {
        if(currentVendor.vendorEvent == null){
            LOGGER.info("You're not associated with an event!");
            serviceProviderScreen();
        }

        currentVendor.vendorEvent.printEventDetails();
        serviceProviderScreen();

    }
    private static void editAddContract() {
        LOGGER.info("This is your current contract.");
        LOGGER.info(currentVendor.contractDescription);
        LOGGER.info("Enter your new contract: ");
        String newContract = scanner.nextLine();
        LOGGER.info("Are you sure you want to edit? y/n");
        if (!scanner.nextLine().equalsIgnoreCase("y")){
            LOGGER.info("Edit failed!");
            serviceProviderScreen();
        }
        currentVendor.setContractDescription(newContract);
        LOGGER.info("Contract edited/added successfully!");
        serviceProviderScreen();
    }
    private static void alterPackage(String alterationType) {
        if(currentVendor.vendorPackages.isEmpty()){
            LOGGER.log(Level.INFO, "Add a package first to {0}", alterationType);
            serviceProviderScreen();
        }
        currentVendor.displayPackages();
        LOGGER.log(Level.INFO, "Enter package number you want to {0}", alterationType);
        int packageNo = scanner.nextInt();
        scanner.nextLine();
        if (packageNo > currentVendor.vendorPackages.size()) {
            numberDoesntExistMessage();
            serviceProviderScreen();
        }
        if(alterationType.equalsIgnoreCase("delete")) {
            currentVendor.vendorPackages.remove(packageNo - 1);
        } else if (alterationType.equalsIgnoreCase("edit")) {
            LOGGER.info("Enter edit:");
            String editedPackage = scanner.nextLine();
            currentVendor.vendorPackages.set(packageNo-1, editedPackage);
        }
        LOGGER.log(Level.INFO, "Package {0}ed successfully!", alterationType);
        serviceProviderScreen();
    }
    private static void addPackage() {
        LOGGER.info("Enter package you want to add:");
        String newPackage = scanner.nextLine();
        currentVendor.addPackage(newPackage);
        LOGGER.info("Package added successfully!");
        serviceProviderScreen();
    }
    private static void showPackages() {
        currentVendor.displayPackages();
        serviceProviderScreen();
    }
    private static void userScreen() {
        LOGGER.info("User Screen");
        LOGGER.info("""
                Please enter your choice:
                1. Manage Events
                2. Register in an Event
                3. Logout
                4. Exit""");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> manageEvents();
            case 2 -> registerInEvent();
            case 3 -> manageUserRegistration();
            case 4 -> {
                System.exit(0);
                return;
            }
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
        userScreen();
    }
    private static void bookVenue() {
        eventManager.printEventsForOrganizer(currentUser);
        LOGGER.info("Enter event number to book a venue for: ");
        Event pickedEvent = selectEvent();
        if(pickedEvent.isNotTheOrganizerOfTheEvent(currentUser)){
            LOGGER.info(NOT_ORGANIZER_MESSAGE);
            manageEvents();
        }
        if (pickedEvent.hasVenue()){
            LOGGER.info("The event is already associated with a venue!");
            manageEvents();
        }
        LOGGER.log(Level.INFO, "Your current budget is: {0}", currentUser.budget);
        printVenues();
        LOGGER.info("Enter venue number to book: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if (venueNo > venueManager.getVenues().size()) {
            numberDoesntExistMessage();
            userScreen();
        }
        Venue pickedVenue = venueManager.getVenues().get(venueNo-1);
        if (pickedVenue.booked){
            LOGGER.info("Venue is booked!");
            bookVenue();
        }
        if(pickedVenue.pricing > currentUser.budget){
            LOGGER.info("Can't afford venue!");
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
        if (eventNo > eventManager.getEvents().size()) {
            numberDoesntExistMessage();
            userScreen();
        }
        eventManager.getEvents().get(eventNo - 1).addAttendee(currentUser);
        LOGGER.info("Registration successful!");
        userScreen();


    }
    private static void manageEvents() {
        LOGGER.info("""
                Enter your choice of management:
                1. Create Event
                2. Edit Event
                3. Delete Event
                4. Display All of the Events
                5. Book a Venue for a Specified Event
                6. Display Attendees of an Event
                7. Filter and Assign Vendors to an Event
                8. Release Vendor of an Event
                9. Release Venue of an Event
                10. Add Budget
                11. Print Budget Report
                12. Reminders and Notifications for Near Events
                13. See Upcoming Events
                14. Go Back to User Screen
                15. Exit""");
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
            case 12 -> displayNearEvents();
            case 13 -> displayUpComingEvents();
            case 14 -> userScreen();
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
        LOGGER.log(Level.INFO, "Enter event number to release the {0} for: ", resourceName);
        Event pickedEvent = selectEvent();
        if (pickedEvent.isNotTheOrganizerOfTheEvent(currentUser)) {
            LOGGER.info(NOT_ORGANIZER_MESSAGE);
            manageEvents();
        }
        if (resourceName.equals("venue") && pickedEvent.hasVenue()) {
            pickedEvent.releaseVenue();
            LOGGER.log(Level.INFO, "The {0} was released successfully!", resourceName);
        } else if (resourceName.equals("vendor") && pickedEvent.hasVendor()) {
            pickedEvent.eventVendor.releaseEvent();
            pickedEvent.releaseVendor();
            LOGGER.log(Level.INFO, "The {0} was released successfully!", resourceName);
        }
        manageEvents();
    }
    private static void addBudget() {
        LOGGER.info("Enter your budget:");
        int budget = scanner.nextInt();
        scanner.nextLine();
        currentUser.setBudget(budget);
    }
    private static Event selectEvent() {
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if (eventNo > eventManager.getEvents().size()) {
            LOGGER.info("Event number does not exist!");
            userScreen();
        }
        return eventManager.getEvents().get(eventNo - 1);
    }
    private static void isOrganizerEventAndHasVendor(Event associatedEvent){
        if (associatedEvent.isNotTheOrganizerOfTheEvent(currentUser)){
            LOGGER.info(NOT_ORGANIZER_MESSAGE);
            assignVendors();
        }
        if(associatedEvent.hasVendor()){
            LOGGER.info("This event already has a vendor!");
            manageEvents();
        }
    }
    private static void assignVendors() {
        if (!eventManager.isOrganizerOfEvent(currentUser)) {
            LOGGER.info("You're not an organizer of an event!");
            manageEvents();
        }
        eventManager.displayEventsForOrganizer(currentUser);
        LOGGER.info("Enter the event number you want to associate the vendor with");

        Event associatedEvent = selectEvent();
        isOrganizerEventAndHasVendor(associatedEvent);
        do {
            LOGGER.info("""
                    Select how to filter vendors:
                    1. Display All Vendors
                    2. Display Vendors by Availability
                    3. Display Vendors by Pricing
                    4. Display Vendors by Location
                    5. Display Vendors by Reviews
                    6. Go Back to User Screen
                    7. Exit""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            LOGGER.log(Level.INFO, "Your current budget is: {0}", currentUser.budget);
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
            LOGGER.info("Want to try and filter again? (press y to try again)");
        } while (scanner.nextLine().equalsIgnoreCase("y"));
        LOGGER.info("Enter vendor you want to contact");
        int vendorNo = scanner.nextInt();
        scanner.nextLine();
        if (vendorNo > login.getNumberOfVendors()) {
            LOGGER.info("Vendor number does not exist!");
            userScreen();
        }
        Vendor selectedVendor = login.vendors.get(vendorNo - 1);
        if (!selectedVendor.availability){
            LOGGER.info("Vendor is not available!");
            assignVendors();
        }
        LOGGER.info(selectedVendor.getContractDescription());
        LOGGER.info("Accept contract? y/n");
        String yesNoQuestion = scanner.nextLine();
        if (yesNoQuestion.equalsIgnoreCase("n") || (currentUser.budget < selectedVendor.pricing)) {
            LOGGER.info("Contract failed!");
            userScreen();
        } else if (yesNoQuestion.equalsIgnoreCase("Y")) {
            currentUser.linkWithVendor(selectedVendor);
            LOGGER.info("Linked with vendor successfully!");
            LOGGER.info("Enter number of a package:");
            selectedVendor.displayPackages();
            int packageChoice = getPackageFromVendor(selectedVendor);
            associatedEvent.setPackage(selectedVendor.getPackageName(packageChoice - 1, currentUser));
            associatedEvent.setVendor(selectedVendor);
            selectedVendor.setEvent(associatedEvent);
            LOGGER.info("Package is added to event successfully!");
        }
        manageEvents();

    }
    private static int getPackageFromVendor(Vendor selectedVendor){
        int packageChoice;
        while (true) {
            packageChoice = scanner.nextInt();
            scanner.nextLine();
            if (packageChoice <= selectedVendor.vendorPackages.size()) {
                return packageChoice;
            }
            LOGGER.info("Package number does not exist!, try again.");
        }
    }
    private static void displayByReviews() {
        LOGGER.info("Enter desired review: ");
        int review = Integer.parseInt(scanner.nextLine());
        login.displayVendorByPrice(review);
    }
    private static void displayByLocation() {
        LOGGER.info("Enter desired location: ");
        login.displayVendorByLocation(scanner.nextLine());
    }
    private static void displayByPricing() {
        LOGGER.info("Enter desired price: ");
        int price = Integer.parseInt(scanner.nextLine());
        login.displayVendorByPrice(price);
    }
    private static void displayAttendees() {
        LOGGER.info("Enter event number: ");
        selectEvent().printAttendees();
    }
    private static void deleteEvent() {
        eventManager.printEventsForOrganizer(currentUser);
        LOGGER.info("Enter event number you want to delete: ");
        Event pickedEvent = selectEvent();
        if (pickedEvent.isNotTheOrganizerOfTheEvent(currentUser)){
            LOGGER.info(NOT_ORGANIZER_MESSAGE);
            manageEvents();
        }
        try {
            pickedEvent.eventVendor.releaseEvent();
        }catch (Exception e){
            LOGGER.info("There is already no vendor");
        }
        eventManager.deleteEvent(pickedEvent);
        LOGGER.info("Event deleted successfully!");
        manageEvents();
    }
    private static void numberDoesntExistMessage(){
        LOGGER.info("Number does not exist!");
    }
    private static void editEvent() {
        eventManager.printEventsForOrganizer(currentUser);
        LOGGER.info("Enter event number: ");
        Event pickedEvent = selectEvent();
        if (pickedEvent.isNotTheOrganizerOfTheEvent(currentUser)){
            LOGGER.info(NOT_ORGANIZER_MESSAGE);
            editEvent();
        }
        eventInformation();

        pickedEvent.updateEvent(date, time, location, theme, description, attendeeCount);
        LOGGER.info("Event updated successfully!");
        manageEvents();
    }
    private static void eventInformation() {

        getDateFromUser();

        LOGGER.info("Enter the Time: ");
        time = scanner.nextLine();

        LOGGER.info("Enter the Location: ");
        location = scanner.nextLine();

        LOGGER.info("Enter the Theme: ");
        theme = scanner.nextLine();

        LOGGER.info("Enter the Description: ");
        description = scanner.nextLine();

        LOGGER.info("Enter the Attendee Count: ");
        String attendeeCountString = scanner.nextLine();
        attendeeCount = Integer.parseInt(attendeeCountString);
    }
    private static void getDateFromUser() {
        boolean validDate = false;
        do {
            LOGGER.info("Enter the Date (yyyy-mm-dd): ");
            date = scanner.nextLine();

            Matcher matcher = DATE_PATTERN.matcher(date);
            if (matcher.matches()) {
                validDate = true;
            } else {
                LOGGER.info("Invalid date format! Please enter the date in yyyy-mm-dd format.");
            }
        } while (!validDate);
    }
    private static void createEvent() {
        eventInformation();
        Event newEvent = new Event(date, time, location, theme, description, attendeeCount, currentUser);
        eventManager.addEvent(newEvent);
        LOGGER.info("Event added successfully!");
        manageEvents();
    }
    private static void adminScreen() {
        LOGGER.info("Admin screen");
        LOGGER.info("""
                Please enter your choice:
                1. Manage Venues
                2. Delete Account
                3. Create Account for "Service Provider"
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
        LOGGER.info("Enter Service Provider Email: ");
        serviceProviderEmail = scanner.nextLine();
        LOGGER.info("Enter Password: ");
        serviceProviderPassword = scanner.nextLine();
        LOGGER.info("Enter Service Provider Category: ");
        serviceProviderCategory = scanner.nextLine();
        LOGGER.info("Enter Service Provider Location: ");
        serviceProviderLocation = scanner.nextLine();
        LOGGER.info("Enter Service Provider Pricing: ");
        serviceProviderPricing = scanner.nextInt();
        scanner.nextLine();
        LOGGER.info("Enter Service Provider Review: ");
        serviceProviderReview = scanner.nextInt();
        scanner.nextLine();
        login.addServiceProvider(serviceProviderEmail, serviceProviderPassword, serviceProviderCategory, serviceProviderLocation, serviceProviderPricing , serviceProviderReview, null);
        adminScreen();
    }
    private static void manageVenues() {
        LOGGER.info("""
                Enter your choice of management:
                1. Add Venue
                2. Edit Venue
                3. Delete Venue
                4. Display the Venues
                5. Display Booked Event
                6. Go Back to User Screen
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
            case 7 -> {
                System.exit(0);
                return;
            }
            default -> LOGGER.info(INVALID_CHOICE_MESSAGE);
        }
        manageVenues();
    }
    private static void displayBookedEvent() {
        LOGGER.info("Enter venue number: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if (venueNo > venueManager.getVenues().size()) {
            numberDoesntExistMessage();
            manageVenues();
        }
        if (venueManager.getVenues().get(venueNo - 1).associatedEvent != null)
            venueManager.getVenues().get(venueNo - 1).associatedEvent.printEventDetails();
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
        if (venueNo > venueManager.getVenues().size()) {
            numberDoesntExistMessage();
            manageVenues();
        }
        venueManager.deleteVenue(venueManager.getVenues().get(venueNo - 1));
        LOGGER.info("Venue deleted successfully!");

    }

    private static void editVenue() {
        LOGGER.info("Enter venue number you want to edit: ");
        int venueNo = scanner.nextInt();
        scanner.nextLine();
        if (venueNo > venueManager.getVenues().size()) {
            numberDoesntExistMessage();
            editVenue();
        }
        venueInformation();
        venueManager.getVenues().get(venueNo - 1).editVenue(name, capacity, amenities, pricing);
        LOGGER.info("Venue updated successfully!");
        manageVenues();
    }
    private static void addVenue() {
        //String venueName, int capacity, String amenities, int pricing
        venueInformation();
        venueManager.addVenue(new Venue(name, capacity, amenities, pricing));
        LOGGER.info("Venue added successfully!");
        manageVenues();
    }
    public static void venueInformation(){
        LOGGER.info("Enter the venue name: ");
        name = scanner.nextLine();
        LOGGER.info("Enter the capacity: ");
        String capacityStr = scanner.nextLine();
        capacity = Integer.parseInt(capacityStr);
        LOGGER.info("Enter the amenities: ");
        amenities = scanner.nextLine();
        LOGGER.info("Enter the pricing: ");
        String pricingStr = scanner.nextLine();
        pricing = Integer.parseInt(pricingStr);
    }
    private static void signUp() {
        LOGGER.info("Enter email you want to sign up with: ");
        String email = scanner.nextLine();
        if (login.emailExists(email)) {
            LOGGER.info("Email exists");
            signUp();
        }
        LOGGER.info("Create password: ");
        String password = scanner.nextLine();

        login.addUser(email, password);
    }
    private static void signIn() {
        whichType = NOT_VALID;
        LOGGER.info("Enter your email: ");
        String email = scanner.nextLine();
        LOGGER.info("Enter your password: ");
        String password = scanner.nextLine();
        whichType = login.getTypeNumber(email, password);

        if (whichType == NOT_VALID) {
            LOGGER.info("Your account doesn't exist. Please proceed to sign up.");
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
}