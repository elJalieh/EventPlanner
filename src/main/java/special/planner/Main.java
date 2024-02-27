package special.planner;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        Login l = new Login();
        l.initializeUsers();
        boolean yo = l.isValid(email, password);
        if(yo){
            System.out.println("welcome");
        }
        else System.out.println("not welcome");
        if (l.logInStatus){
            System.out.printf("this is the menu");
        }




    }
}