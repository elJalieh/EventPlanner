package special.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Vendor {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    String email;
    String password;
    String category;
    String location;
    boolean availability;
    int pricing;
    int reviews;
    List<String> vendorPackages = new ArrayList<>();
    String contractDescription;
    User booker;
    Event vendorEvent;

    public Vendor( String email, String password, String Category, String Location,
                    int Pricing, int Reviews, String contractDescription){
        this.email = email;
        this.password = password;
        this.category=Category;
        this.location=Location;
        this.availability=true;
        this.pricing=Pricing;
        this.reviews=Reviews;
        this.contractDescription = contractDescription;
        this.vendorEvent=null;
    }
    void setBooker(User booker){
        this.booker = booker;
        this.availability = false;
    }
    public String getContractDescription(){
        return this.contractDescription;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void addPackage(String p) {
        vendorPackages.add(p);
    }

    public String getPackageName(int packageIndex, User Booker) {
        return (this.booker.equals(Booker)) ? vendorPackages.get(packageIndex) : "Not Permitted";

    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }

    public boolean isAvailable() {
        return this.availability;
    }

    public void displayPackages() {
        int index = 1;
        for (String i :
                vendorPackages) {
            LOGGER.info(index +". "+ i + "\n");
            index++;
        }
    }

    public boolean isPackageInlist(String package0) {
        return this.vendorPackages.contains(package0);
    }

    public void deletePackage(String package0) {
        int toRemove = -1;
        for (String i: vendorPackages){
            if(i.equals(package0)){
                toRemove = vendorPackages.indexOf(i);
                break;
            }
        }
        vendorPackages.remove(toRemove);
    }

    public int getPackageNum(String search) {
        int index = -1;
        for (String i: vendorPackages){
            if(i.equals(search)){
                index = vendorPackages.indexOf(i);
                break;
            }
        }
        return index;
    }

    public boolean viewPackages() {
        for (String aPackage : vendorPackages) LOGGER.info(aPackage);
        return true;
    }

    public Boolean checkBooker() {
        if(booker!=null){
            vendorEvent.printEventDetails();
            return true;
        }
        return false;
    }

    public void setEvent(Event associatedEvent) {
        this.vendorEvent = associatedEvent;
    }

    public void releaseEvent() {
        this.vendorEvent = null;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }


}
