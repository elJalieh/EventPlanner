package special.planner;

import java.util.ArrayList;
import java.util.List;

public class Login {
    String admin = "Admin";
    String serviceProvider = "Service Provider";
    String user = "User";
    List<User> users=new ArrayList<>();
    boolean logInStatus;


    public void initializeUsers(){
        User u1=new User("alaraid2003@gmail.com","123", admin);
        User u2=new User("a2y2m2a2n@gmail.com","123", serviceProvider);
        User u3=new User("mo.matar123@gmail.com","123", user);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        logInStatus = false;
        //System.out.println("enter email:");
    }

    public boolean isValid(String email, String password) {
        for (User i :
                users) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                //logInStatus = true;
                return true;
            }

        }
        return false;


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
}








