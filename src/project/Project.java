package project;

import crud.User;
import exception.DeadlineIsBeforeCreation;
import exception.MemberIsAlreadyInProject;
import exception.MemberNotFound;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Project {
    private UUID id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private LocalDate deadline;
    private ArrayList<User> involvedMembers;
    private ArrayList<Task> taskList;

    public Project(String name, String description, LocalDate creationDate, LocalDate deadline) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.involvedMembers = new ArrayList<>();
        this.taskList = new ArrayList<>();
    }

    public void addTask(){
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan judul task: ");
        String taskName = input.nextLine();
        System.out.print("Masukkan deskripsi task: ");
        String taskDescription = input.nextLine();
        System.out.print("Masukkan deadline task (DD-MM-YYYY): ");
        boolean safe = true;
        while(safe){
            try {
                LocalDate deadline = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                if(deadline.isBefore(creationDate)) throw new DeadlineIsBeforeCreation();
                else{
                    safe = false;
                    Task task = new Task(taskName, taskDescription, deadline);
                    taskList.add(task);
                }
            } catch (DeadlineIsBeforeCreation e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addMember(User user, Project project) {
        try{
            for(User member : involvedMembers){
                if(member.getUsername().equals(user.getUsername())) throw new MemberIsAlreadyInProject();
            }
            involvedMembers.add(user);
            user.addProject(project);
            System.out.println(user.getUsername() + " has been added to " + project.getName());
            System.out.println("Member added successfully!");
        }catch(MemberIsAlreadyInProject e){
            System.out.println(e.getMessage());
        }
    }

    public void removeMember(User user, Project project) {
        try{
            boolean found = false;
            for(User member : involvedMembers){
                if(member.getUsername().equals(user.getUsername())) found = true;
            }
            if(!found) throw new MemberNotFound();
            involvedMembers.remove(user);
            user.removeProject(project);
            System.out.println(user.getUsername() + " has been removed from " + project.getName());
            System.out.println("Member removed successfully!");
        }catch(MemberNotFound e){
            System.out.println(e.getMessage());
        }
    }
    
    public void showInvolvedMembers(){
        System.out.println("Members: ");
        for (User member : involvedMembers) {
            System.out.println("1. " + member.getUsername());
        }
    }

    public void showTasks(){
        System.out.println("Tasks: ");
        for (Task task : taskList) {
            System.out.println("1. " + task.getName());
        }
    }

    public void showData(){
        System.out.println("===========================================");
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Created date: " + creationDate);
        System.out.println("Deadline: " + deadline);
        showInvolvedMembers();
        showTasks();
        System.out.println("===========================================");
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }

    public ArrayList<User> getInvolvedMembers(){
        return involvedMembers;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }  

    public LocalDate getDeadline() {
        return deadline;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

}
