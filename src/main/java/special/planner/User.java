package special.planner;

public class User {
    String email;
    String password;
    String type;

    String firstName;
    String lastName;
    boolean Organizer;

    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
        firstName="Mahmood";
        lastName="Ahmed";
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

    public boolean isOrganizer(){
        return Organizer;
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


}
