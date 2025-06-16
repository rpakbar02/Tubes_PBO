package crud;

import java.util.Scanner;
import java.util.UUID;

public class Admin extends Role{
    public Admin() {
        id = UUID.randomUUID();
        role = "Admin";
    }

    public void assignRoles(User user){
        Scanner input = new Scanner(System.in);
        System.out.println("Assigning roles to " + user.getUsername());
        System.out.println("Choose a role for " + user.getUsername() + ":\n1. Admin\n2. Manager\n3. Member");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                user.setRole(new Admin());
                break;
            case 2:
                user.setRole(new Manager());
                break;
            case 3:
                user.setRole(new Member());
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                assignRoles(user);
                break;
        }
    }
    public User createUser(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter email: ");
        String email = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        User user = new User(username, email, password, new Member());
        assignRoles(user);
        System.out.println("User created: " + user.getUsername());
        // input.close();
        return user;
    }
    public void deleteUser(User user){
        System.out.println("Deleting " + user.getUsername());
        user = null;
        System.gc();
    }
}
