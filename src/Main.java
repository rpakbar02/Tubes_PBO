import crud.Admin;
import crud.Database;
import crud.Manager;
import crud.Member;
import crud.Role;
import crud.User;
import java.io.IOException;
import java.util.Scanner;
import project.Project;

public class Main {
    public static void main(String[] args) throws IOException {
        Role adm = new Admin();
        Role member = new Member();
        Role manager = new Manager();
        Database.register(adm);
        Scanner input = new Scanner(System.in);
        while (true) { 
            System.out.println("1. Login\n2. Register\n3. Exit"); 
            System.out.print("Pilih: "); 
            int pilihan = input.nextInt(); 
            if (pilihan == 1) { 
                System.out.print("Please Input Username: ");
                String username = input.next();
                System.out.print("Please Input Password: ");
                String password = input.next();
                User user = Database.logIn(username, password); 
                if(user != null) {
                    System.out.println("Login Success!");
                    if (user.getRole() instanceof Manager man) {
                        int option = 0;
                        System.out.println("Welcome " + user.getUsername() + " as Manager!");
                        while(option != 6){
                            System.out.println("1. View Profile\n2. Update Profile\n3. Create Project\n4. Manage Project\n5. Manage Project Members\n6. Exit");
                            System.out.print("Your Choice: ");
                            option = input.nextInt();
                            switch (option) {
                                case 1:
                                    user.viewProfile();
                                    break;
                                case 2:
                                    user.updateProfile();
                                    break;
                                case 3:
                                    user.createProject();
                                    break;
                                case 4:
                                    man.manageProject(user.getProjects());
                                    break;
                                case 5:
                                    System.out.println("Please enter username of member: ");
                                    String memusername = input.next();
                                    User useradd = Database.getUser(memusername);
                                    if(useradd == null){
                                        System.out.println("Member not found. Please try again.");
                                        break;
                                    }else{
                                        man.manageProject(user.getProjects(), useradd);
                                    }                                    
                                    break;
                                case 6:
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        }
                    } else if (user.getRole() instanceof Member mem) {
                        System.out.println("Welcome " + user.getUsername() + " as Member!");
                        int option = 0;
                        while(option != 5){
                            System.out.println("1. View Profile\n2. Update Profile\n3. View Project\n4. Write Report\n5. Exit");
                            System.out.print("Your Choice: ");
                            option = input.nextInt();
                            switch (option) {
                                case 1:
                                    user.viewProfile();
                                    break;
                                case 2:
                                    user.updateProfile();
                                    break;
                                case 3:
                                    System.out.println("Choose a project to view: ");
                                    for(int i = 0; i < user.getProjects().size(); i++){
                                        System.out.println(i + 1 + ". " + user.getProjects().get(i).getName());
                                    }
                                    int choice = input.nextInt();
                                    user.viewProject(user.getProjects().get(choice - 1));
                                    break;
                                case 4:
                                    System.out.println("Choose a project: ");
                                    for(int i = 0; i < user.getProjects().size(); i++){
                                        System.out.println(i + 1 + ". " + user.getProjects().get(i).getName());
                                    }
                                    int choice1 = input.nextInt();
                                    Project project = user.getProjects().get(choice1 - 1);
                                    mem.writeReport(project, project.getTaskList().get(0), user);
                                    break;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                           }
                        }
                    } else if (user.getRole() instanceof Admin admin) {
                        System.out.println("Welcome " + user.getUsername() + " as Admin!");
                        int option1 = 0;
                        while(option1 != 5){
                            User userchoice = user;
                            System.out.println("1. View Profile\n2. Update Profile\n3. Assign Roles\n4. Delete User\n5. Exit");
                            System.out.print("Your Choice: ");
                            option1 = input.nextInt();
                            switch (option1) {
                                case 1:
                                    user.viewProfile();
                                    break;
                                case 2:
                                    user.updateProfile();
                                    break;
                                case 3:
                                    System.out.println("Choose a user to assign roles: ");
                                    for(int i = 0; i < Database.getUserList().size(); i++){
                                        User user1 = Database.getUserList().get(i);
                                        System.out.println(i + 1 + ". " + user1.getUsername());
                                    }
                                    System.out.print("Your Choice: ");
                                    int choice = input.nextInt();
                                    userchoice = Database.getUserList().get(choice - 1);
                                    admin.assignRoles(userchoice);
                                    break;
                                case 4:
                                    System.out.println("Choose a user to delete: ");
                                    for(int i = 0; i < Database.getUserList().size(); i++){
                                        User user1 = Database.getUserList().get(i);
                                        System.out.println(i + 1 + ". " + user1.getUsername());
                                    }
                                    System.out.print("Your Choice: ");
                                    int choice1 = input.nextInt();
                                    userchoice = Database.getUserList().get(choice1 - 1);
                                    admin.deleteUser(userchoice);
                                    break;
                                case 5:
                                    break;
                                }
                            }
                    }
                }
            } else if (pilihan == 2) {
                Database.register(member);
                System.out.println("Register Success!");
            } else if (pilihan == 3) { 
                break;
            }
        }
    }

}