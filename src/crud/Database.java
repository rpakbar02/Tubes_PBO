package crud;

import java.util.ArrayList;

public class Database {
    private static ArrayList<User> userList = new ArrayList();

    public static void addUser(User user){
        userList.add(user);
    }

    public static void removeUser(User user){
        userList.remove(user);
    }

    public static User logIn(String username, String password){
        for(User user: userList){
            if(user.getUsername() == username && user.getPassword() == password){
                
            }
        }
    }
}
