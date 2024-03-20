package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Login {
    String admin = "Admin";
    String serviceProvider = "Service Provider";
    String user = "User";
    List<User> users=new ArrayList<>();
    List<Vendor> vendors = new ArrayList<>();

    boolean logInStatus;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());




    public void initializeUsers(){
        User u1=new User("alaraid2003@gmail.com","123", admin);
        //User u1=new User("a","123", admin);
        User u2=new User("a2y2m2a2n@gmail.com","123", user);
        Vendor v =new Vendor("v1","123", "singers", "salfeet",
        1000, 3, "my price is 1000 brother take it or leave it");
        Vendor v2 =new Vendor("v2","123", "resturants", "Nablus",
        5000, 4, "my price is 5000 brother take it or leave it");
        Vendor v3 =new Vendor("v3","123", "cleaners", "betoonia",
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
        vendors.add(v);
        vendors.add(v2);
        vendors.add(v3);
        logInStatus = false;
        //System.out.println("enter email:");
    }

    public int isValid(String email, String password) {
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
//    public String getUser(String email, String password){
//        for (User i :
//                users) {
//            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
//                logInStatus = true;
//                return true;
//            }
//
//        }
//        return null;
//    }

    public void addServiceProvider(String email, String password,String Category, String Location, int Pricing, int Reviews, String contractDescription){
        Vendor newVendor = new Vendor(email, password, Category, Location, Pricing, Reviews, contractDescription);
        vendors.add(newVendor);
    }

    public void removeVendor(Vendor V){
        vendors.remove(V);
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
            LOGGER.info(index + ". " + "Vendor email: " + v.email + " location: "+ v.Location + " availability: " + v.Availability +"\n"
                    + "Review: "+ v.Reviews + " price: " + v.Pricing + "\n");
            index++;
        }
    }

    public String displayVendorByLocation(String location) {
        String email = "";
        int index = 1;
        for (Vendor v :
                vendors) {
            if(Objects.equals(v.Location, location)){
                LOGGER.info(index + ". " + "Vendor email: " + v.email + " location: "+ v.Location + " availability: " + v.Availability +"\n"
                + "Review: "+ v.Reviews + " price: " + v.Pricing + "\n");
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
            if(Objects.equals(v.Pricing, price)){
                LOGGER.info(index + ". " +"Vendor email: " + v.email + " location: "+ v.Location + " availability: " + v.Availability +"\n"
                        + "Review: "+ v.Reviews + " price: " + v.Pricing + "\n");
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
            if(v.Availability == b){
                LOGGER.info(index + ". " +"Vendor email: " + v.email + " location: "+ v.Location + " availability: " + v.Availability +"\n"
                        + "Review: "+ v.Reviews + " price: " + v.Pricing + "\n");
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
            if(v.Reviews == int1){
                LOGGER.info(index + ". " +"Vendor email: " + v.email + " location: "+ v.Location + " availability: " + v.Availability +"\n"
                        + "Review: "+ v.Reviews + " price: " + v.Pricing + "\n");
                email = v.email;
            }
                index++;
        }
        return email;
    }

    public int getNumberOfVendors(){
        return this.vendors.size();
    }


}








