package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Vendor {
    String email;
    String password;
    String Category;
    String Location;
    boolean Availability;
    int Pricing;
    int Reviews;
    List<String> Packages = new ArrayList<>();
    String contractDescription;
    User Booker;

    public Vendor( String email, String password, String Category, String Location,
                    int Pricing, int Reviews, String contractDescription){
        this.email = email;
        this.password = password;
        this.Category=Category;
        this.Location=Location;
        this.Availability=true;
        this.Pricing=Pricing;
        this.Reviews=Reviews;
        this.contractDescription = contractDescription;
    }
    void setBooker(User Booker){
        this.Booker = Booker;
        this.Availability = false;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }

    public void addPackage(String p) {
        Packages.add(p);
    }

    public String getPackageName(int packageIndex, User Booker) {
        return (this.Booker.equals(Booker)) ? Packages.get(packageIndex-1) : "Not Permitted";

    }

    public boolean isAvailable() {
        return this.Availability;
    }
    //    public Vendor(  String Location, String Availability, int Pricing, float Reviews){
//        this.Location=Location;
//        this.Availability=Availability;
//        this.Pricing=Pricing;
//        this.Reviews=Reviews;
//    }
//    public Vendor(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
//    public String getEmail() {
//        return email;
//    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

//    public String getPassword() {
//        return password;
//    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

}
