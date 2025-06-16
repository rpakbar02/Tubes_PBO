package project;

import crud.User;
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
        LocalDate deadline = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Task task = new Task(taskName, taskDescription, deadline);
        taskList.add(task);
    }

    public void addMember(User user, Project project) {
        involvedMembers.add(user);
        user.addProject(project);
    }

    public void removeMember(User user, Project project) {
        involvedMembers.remove(user);
        user.removeProject(project);
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
}
