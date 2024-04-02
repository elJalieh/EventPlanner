package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    String admin = "Admin";
    String user = "User";
    Mailing mail;
    List<User> users=new ArrayList<>();
    List<Vendor> vendors = new ArrayList<>();
    boolean logInStatus;
    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
    public void initializeUsers(){
        User u1 = new User("alaraid2003@gmail.com","123", admin);
        User u2 = new User("a2y2m2a2n@gmail.com","123", user);
        User u3 = new User("mo.matar123@gmail.com","123", user);
        Vendor v = new Vendor("s12112161@stu.najah.edu","123", "singers", "Salfit",
        1000, 3, "my price is 1000 brother take it or leave it");
        v.addPackage("Party Package");
        v.addPackage("Wedding Package");
        users.add(u1);
        users.add(u2);
        users.add(u3);
        vendors.add(v);
        logInStatus = false;
    }
    public int getTypeNumber(String email, String password) {
        if(email.isEmpty() || password.isEmpty()) return 0;
        for (User i :
                users) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                mail = new Mailing(i.getEmail());
                mail.sendVerificationCode();
                logInStatus = true;
                return 1;
            }
        }
        for (Vendor i :
                vendors) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                mail = new Mailing(i.getEmail());
                mail.sendVerificationCode();
                logInStatus = true;
                return 2;
            }
        }
        return 0;
    }
    public boolean confirmLogin(int verificationCode){
        return mail.verificationCode == verificationCode;
    }
    public boolean isLoggedIn(){
        return this.logInStatus;
    }
    public void setLogInStatus(boolean logInStatus) {
        this.logInStatus = logInStatus;
    }
    public boolean emailExists(String email){
        for (User i :
                users) {
            if (i.getEmail().equals(email) ) {
                return true;
            }
        }
        return false;
    }
    public boolean vendorExists(String email){
        for (Vendor i :
                vendors) {
            if (i.getEmail().equals(email) ) {
                return true;
            }
        }
        return false;
    }
    public User getCurrentUser(String email, String password){
        if(email.isEmpty() || password.isEmpty()) return null;
        for (User i :
                users) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                return i;
            }
        }
        return null;
    }
    public Vendor getCurrentVendor(String email, String password){
        if(email.isEmpty() || password.isEmpty()) return null;
        for (Vendor i :
                vendors) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                return i;
            }
        }
        return null;
    }
    public void addUser(String email, String password) {
        User newUser = new User(email, password, user);
        users.add(newUser);
    }
    public void addServiceProvider(String email, String password,String category, String location, int pricing, int reviews, String contractDescription){
        Vendor newVendor = new Vendor(email, password, category, location, pricing, reviews, contractDescription);
        vendors.add(newVendor);
    }
    public void deleteUser(String email){

        int toRemove;
        for (User i: users){
            if(i.email.equals(email)){
                toRemove = users.indexOf(i);
                users.remove(toRemove);
                break;
            }
        }
    }
    public void addVendor(Vendor vendor) {
        vendors.add(vendor);
    }
    public void displayAllVendors(){
        int index = 1;
        for (Vendor v :
                vendors) {
            printVendorDetails(index, v);
            index++;
        }
    }
    public String displayVendorByLocation(String location) {
        String email = "";
        int index = 1;
        for (Vendor v :
                vendors) {
            if(Objects.equals(v.location, location)){
                printVendorDetails(index, v);
                email = v.email;
            }
                index++;
        }
        return email;
    }
    public String displayVendorByPrice(Integer price) {
        String email = "";
        int index = 1;
        for (Vendor v :
                vendors) {
            if(Objects.equals(v.pricing, price)){
                printVendorDetails(index, v);
                email = v.email;
            }
                index++;
        }
        return email;
    }
    public String displayVendorByAvailability(boolean b) {
        String email = "";
        int index = 1;
        for (Vendor v :
                vendors) {
            if(v.availability == b){
                printVendorDetails(index, v);
                email = v.email;
            }
                index++;
        }
        return email;
    }
    public String displayVendorByReview(Integer int1) {
        String email = "";
        int index = 1;
        for (Vendor v :
                vendors) {
            if(v.reviews == int1){
                printVendorDetails(index, v);
                email = v.email;
            }
                index++;
        }
        return email;
    }
    public int getNumberOfVendors(){
        return this.vendors.size();
    }
    public static void printVendorDetails(int index, Vendor v){
        LOGGER.log(Level.INFO,
                "{0}. Vendor email: {1} Location: {2} Availability: {3}\n" +
                        "Review: {4} Price: {5}",
                new Object[]{index, v.email, v.location, v.availability, v.reviews, v.pricing});
    }
}

