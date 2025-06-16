package crud;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private static ArrayList<User> userList = new ArrayList();

    public static void addUser(User user){
        userList.add(user);
    }

    public static void removeUser(User user) throws IOException{
        userList.remove(user);
        writeUserData(userList);
    }

    public static User getUser(String username){
        for(User user: userList){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public static User logIn(String username, String password) throws IOException{
        User pick = null;
        boolean found = false;
        readUserData(userList);
        for(User user: userList){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                pick = user;
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("User not found. Please try again.");
        }
        return pick;
    }

    public static void register(Role member) throws IOException{
        Scanner input = new Scanner(System.in);
        System.out.println("Please Input Username: ");
        String username = input.next();
        System.out.println("Please Input Password: ");
        String password = input.next();
        System.out.println("Please input email: ");
        String email = input.next();
        User user = new User(username, email, password, member);
        userList.add(user);
        writeUserData(userList);
    }

    public static void writeUserData(ArrayList<User> userList) throws IOException{
        try {
            FileWriter fileWriter = new FileWriter("Tubes_PBOs\\src\\DataBase\\users.txt");
            for (User user : userList) {
                fileWriter.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getRole().getRole() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readUserData(ArrayList<User> userList) throws IOException{
        Role admin = new Admin();
        Role manager = new Manager();
        Role member = new Member();
        FileReader fr = null;
        try {
            fr = new FileReader("Tubes_PBOs\\src\\DataBase\\users.txt");
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        int ch;
        int i = 0;
        String username = "";
        String email = "";
        String password = "";
        String role = "";
        userList.clear();
        while((ch = fr.read()) != -1) {
            if(ch == ',' || ch == '\n'){
                i++;
                if(i == 4){
                    if(role.equals("Admin")) {
                        User user = new User(username, email, password, admin);
                        userList.add(user);
                    }
                    else if(role.equals("Manager")) {
                        User user = new User(username, email, password, manager);
                        userList.add(user);
                    }
                    else if(role.equals("Member")) {
                        User user = new User(username, email, password, member);
                        userList.add(user);
                    }
                    username = "";
                    email = "";
                    password = "";
                    role = "";
                }
                i %= 4;
            }
            else{
                switch (i) {
                    case 0:
                        username += (char) ch;
                        break;
                    case 1:
                        email += (char) ch;
                        break;
                    case 2:
                        password += (char) ch;
                        break;
                    case 3:
                        role += (char) ch;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }
}
