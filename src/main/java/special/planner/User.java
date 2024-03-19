package special.planner;

import java.util.Objects;

public class User {
    String email;
    String password;
    String type;
    boolean Organizer;
    boolean Admin = false;
    int budget;


    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;

    }

    public User(String email, String password) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setAsOrganizer(){
        this.Organizer = true;
    }
    public void setAsNotOrganizer(){
        this.Organizer = false;

    }
    public boolean isAdmin(){
        return Objects.equals(this.getType(), "Admin");
    }

    public boolean isServiceProvider(){
        return Objects.equals(this.getType(), "Service Provider");
    }
    public boolean isOrganizer(){
        return Organizer;
    }
    public void setAsAdmin(){this.Admin = true;}

    public void linkWithVendor( Vendor V) {
        V.setBooker(this);
    }
    public void setBudget(int budgetValue){
        this.budget = budgetValue;
    }

}
