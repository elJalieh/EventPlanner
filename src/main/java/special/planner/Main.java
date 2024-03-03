package special.planner;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    static User currentUser;
    static EventManagement manager;
    static Scanner scanner = new Scanner(System.in);
   static Login login = new Login();
    public static final String ADMIN = "Admin";
    public static final String USER = "User";
    public static final String SERVICE_PROVIDER = "Service Provider";
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
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
        LOGGER.info("please enter your choice\n1. manage events\n2. register in an event\n3. logout\n4. exit");
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

    private static void registerInEvent() {
        manager.printEvents();
        LOGGER.info("enter event number to register: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > manager.Events.size()){
            LOGGER.info("number does not exist!");
            registerInEvent();
        }
        manager.Events.get(eventNo-1).addAttendee(currentUser);
        LOGGER.info("registration successful!");
        userScreen();


    }

    private static void manageEvents() {
        LOGGER.info("enter your choice of management:\n1. create event\n2. edit event\n3. delete event\n4. display the events\n5.display attendees of an event\n6. go back to user screen\n7. exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> createEvent();
            case 2 -> editEvent();
            case 3 -> deleteEvent();
            case 4 -> manager.printEvents();
            case 5 -> displayAttendees();
            case 6 -> userScreen();
            case 7 -> System.exit(0);

            default -> LOGGER.info("Invalid choice! Please try again.");
        }
        userScreen();
    }

    private static void displayAttendees() {
        LOGGER.info("enter event number: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > manager.Events.size()){
            LOGGER.info("event number does not exist!");
            displayAttendees();
        }
        manager.Events.get(eventNo-1).printAttendees();

    }


    private static void deleteEvent() {
        LOGGER.info("enter event number: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > manager.Events.size()){
            LOGGER.info("event number does not exist!");
            deleteEvent();
        }
        manager.deleteEvent(manager.Events.get(eventNo-1));
        LOGGER.info("event updated successfully!\n");
        manageEvents();
    }

    private static void editEvent() {
        LOGGER.info("enter event number: ");
        int eventNo = scanner.nextInt();
        scanner.nextLine();
        if(eventNo > manager.Events.size()){
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
        manager.Events.get(eventNo-1).updateEvent(date, time, location, theme, description, attendeeCount);
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
        manager = new EventManagement();
        manager.addEvent(new Event(date, time, location, theme, description, attendeeCount, currentUser));
        manager.addEvent(new Event(date, time, location, theme, description, attendeeCount, currentUser));
        manager.addEvent(new Event(date, time, location, theme, description, attendeeCount, currentUser));
        LOGGER.info("event added successfully!\n");
        manageEvents();



    }

    private static void adminScreen() {
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
        boolean yo = login.isValid(email, password);
        currentUser = login.getCurrentUser(email, password);
        if(currentUser != null){
            LOGGER.info("welcome, " + currentUser.email);
        }
        else LOGGER.info("not welcome");

    }
}