package special.planner;

import java.util.Objects;

public class User {
    String email;
    String password;
    String type;
    boolean organizer;
    boolean admin = false;
    int budget;
    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
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
    public String getType() {
        return type;
    }
    public void setAsOrganizer(){
        this.organizer = true;
    }
    public boolean isAdmin(){
        return Objects.equals(this.getType(), "Admin");
    }
    public boolean isOrganizer(){
        return organizer;
    }
    public void setAsAdmin(){
        this.admin = true;
    }
    public void linkWithVendor( Vendor v) {
        v.setBooker(this);
    }
    public void setBudget(int budgetValue){
        this.budget = budgetValue;
    }
}
