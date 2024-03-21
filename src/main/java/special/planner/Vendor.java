package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Vendor {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

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
    Event vendorEvent;

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
        this.vendorEvent=null;
    }
    void setBooker(User Booker){
        this.Booker = Booker;
        this.Availability = false;
    }
    public String getContractDescription(){
        return this.contractDescription;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }

    public void addPackage(String p) {
        Packages.add(p);
    }

    public String getPackageName(int packageIndex, User Booker) {
        return (this.Booker.equals(Booker)) ? Packages.get(packageIndex) : "Not Permitted";

    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }

    public boolean isAvailable() {
        return this.Availability;
    }

    public void displayPackages() {
        int index = 1;
        for (String i :
                Packages) {
            LOGGER.info(index +". "+ i + "\n");
            index++;
        }
    }

    public boolean isPackageInlist(String package0) {
        return this.Packages.contains(package0);
    }

    public void deletePackage(String package0) {
        int toRemove = -1;
        for (String i: Packages){
            if(i.equals(package0)){
                toRemove = Packages.indexOf(i);
                break;
            }
        }
        Packages.remove(toRemove);
    }

    public int getPackageNum(String search) {
        int index = -1;
        for (String i: Packages){
            if(i.equals(search)){
                index = Packages.indexOf(i);
                break;
            }
        }
        return index;
    }

    public boolean viewPackages() {
        for (int i = 0; i < Packages.size(); i++) LOGGER.info(Packages.get(i));
        return true;
    }

    public Boolean checkBooker() {
        if(Booker!=null){
            vendorEvent.printEventDetails();
            return true;
        }
        return false;
    }

    public void setEvent(Event associatedEvent) {
        this.vendorEvent = associatedEvent;
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
