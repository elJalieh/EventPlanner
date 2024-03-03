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
        logInStatus = false;
        //System.out.println("enter email:");
    }

    public boolean isValid(String email, String password) {
        if(email.isEmpty() || password.isEmpty()) return false;
        for (User i :
                users) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                logInStatus = true;
                return true;
            }

        }
        return false;
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

    public void addServiceProvider(String email, String password) {
        User newUser = new User(email, password, serviceProvider);
        users.add(newUser);
    }

    public void deleteUser(User u){ users.remove(u);}
}








