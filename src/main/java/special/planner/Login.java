package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Login {
    String admin = "Admin";
    String user = "User";
    List<User> users=new ArrayList<>();
    List<Vendor> vendors = new ArrayList<>();
    boolean logInStatus;
    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
    public void initializeUsers(){
        User a=new User("a","123", admin);
        User u1=new User("alaraid2003@gmail.com","123", admin);
        User u2=new User("a2y2m2a2n@gmail.com","123", user);
        Vendor v =new Vendor("v1","123", "singers", "Salfit",
        1000, 3, "my price is 1000 brother take it or leave it");
        Vendor v2 =new Vendor("v2","123", "Restaurant", "Nablus",
        5000, 4, "my price is 5000 brother take it or leave it");
        Vendor v3 =new Vendor("v3","123", "cleaners", "Betoonia",
        4000, 5, "my price is 4000 brother take it or leave it");
        v.addPackage("7afleh");
        v.addPackage("7aaafleeeeh");
        v.addPackage("daance");
        v2.addPackage("cookies");
        v2.addPackage("catering");
        v2.addPackage("sharshaf");
        v3.addPackage("broom");
        v3.addPackage("water");
        v3.addPackage("cleaner");

        User u3=new User("mo.matar123@gmail.com","123", user);
        User u4=new User("u1","123", user);
        User u5=new User("u2","123", user);
        User u6=new User("u3","123", user);
        User u7=new User("u4","123", user);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        users.add(u7);
        users.add(a);
        vendors.add(v);
        vendors.add(v2);
        vendors.add(v3);
        logInStatus = false;
    }
    public int getTypeNumber(String email, String password) {
        if(email.isEmpty() || password.isEmpty()) return 0;
        for (User i :
                users) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                logInStatus = true;
                return 1;
            }
        }
        for (Vendor i :
                vendors) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                logInStatus = true;
                return 2;
            }
        }
        return 0;
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
        int toRemove = -1;
        for (User i: users){
            if(i.email.equals(email)){
                toRemove = users.indexOf(i);
                break;
            }
        }
        users.remove(toRemove);
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
        LOGGER.info(index + ". " + "Vendor email: " + v.email + " Location: "+ v.location + " Availability: " + v.availability +"\n"
                + "Review: "+ v.reviews + " Price: " + v.pricing);
    }
}

