package crud;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class Admin extends Role implements AdmPrivilege{
    public Admin() {
        id = UUID.randomUUID();
        role = "Admin";
    }

    public void assignRoles(User user) throws IOException{
        Scanner input = new Scanner(System.in);
        System.out.println("Assigning roles to " + user.getUsername());
        System.out.println("Choose a role for " + user.getUsername() + ":\n1. Admin\n2. Manager\n3. Member");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                user.setRole(new Admin());
                System.out.println("Role assigned as Admin!");
                Database.writeUserData(Database.getUserList());
                break;
            case 2:
                user.setRole(new Manager());
                System.out.println("Role assigned as Manager!");
                Database.writeUserData(Database.getUserList());
                break;
            case 3:
                user.setRole(new Member());
                System.out.println("Role assigned as Member!");
                Database.writeUserData(Database.getUserList());
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                assignRoles(user);
                break;
        }
    }

    public void deleteUser(User user) throws IOException{
        System.out.println("Deleting " + user.getUsername());
        Database.removeUser(user);
    }
}
