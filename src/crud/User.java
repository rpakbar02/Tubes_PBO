package crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import project.Project;

public class User {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private ArrayList<Project> projects;

    public User(String username, String email, String password, Role role) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        projects = new ArrayList<>();
    }

    public void updateProfile(){
        System.out.println("Choose what you want to update:\n1. Username\n2. Email\n3. Password\n4. Exit");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter new username: ");
                setUsername(input.next());
                break;
            case 2:
                System.out.println("Enter new email: ");
                setEmail(input.next());
                break;
            case 3:
                System.out.println("Enter new password: ");
                setPassword(input.next());
                break;
            case 4:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                updateProfile();
        }
    }

    public void viewProfile(){
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role.getRole());
        System.out.println("Projects: ");
        for (Project project : projects) {
            System.out.println("1." + project.getName());
        }
    }

    public void viewProject(Project project){
        System.out.println("Name: " + project.getName());
        System.out.println("Description: " + project.getDescription());
        System.out.println("Created date: " + project.getCreationDate());
        System.out.println("Deadline: " + project.getDeadline());
        project.showInvolvedMembers();
        project.showTasks();
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public void removeProject(Project project){
        projects.remove(project);
    }

    public void createProject(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter project name: ");
        String name = input.nextLine();
        System.out.print("Enter project description: ");
        String description = input.nextLine();
        LocalDate creationDate = LocalDate.now();
        System.out.print("Enter project deadline (DD-MM-YYYY): ");
        LocalDate deadline = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Project project = new Project(name, description, creationDate, deadline);
        project.addMember(this, project);
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
