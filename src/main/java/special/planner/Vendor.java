package special.planner;

import java.util.logging.Logger;

public class Vendor {
    String email;
    String password;
    String Category;
    String Location;
    String Availability;
    int Pricing;
    float Reviews;

    public Vendor( String email, String password, String Category, String Location, String Availability, int Pricing, float Reviews){
        this.email=email;
        this.password=password;
        this.Category=Category;
        this.Location=Location;
        this.Availability=Availability;
        this.Pricing=Pricing;
        this.Reviews=Reviews;
    }
    public Vendor(  String Location, String Availability, int Pricing, float Reviews){
        this.Location=Location;
        this.Availability=Availability;
        this.Pricing=Pricing;
        this.Reviews=Reviews;
    }
    public Vendor(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
