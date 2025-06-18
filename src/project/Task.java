package project;
import crud.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Task {
    private UUID id;
    private String name;
    private String description;
    private LocalDate deadline;
    private boolean completed;
    private ArrayList<User> assignedMembers;
    private ArrayList<Subtask> cards;
    private ArrayList<ProgressReport> reports;
    private ArrayList<Workload> workloads;

    public Task(String name, String description, LocalDate deadline) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.completed = false;
        assignedMembers = new ArrayList<>();
        cards = new ArrayList<>();
        reports = new ArrayList<>();
        workloads = new ArrayList<>();
    }

    public void toggleCompleted() {
        if(this.completed){
            this.completed = false;
        }
        else{
            this.completed = true;
        }
    }

    public void addSubtask() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the subtask: ");
        String name = input.nextLine();
        System.out.println("Please write the description of the subtask: ");
        String description = input.nextLine();
        System.out.println("Please enter the estimated hours of the subtask: ");
        int estimatedHours = input.nextInt();
        input.nextLine();
        System.out.println("Please enter the deadline of the subtask (DD-MM-YYYY): ");
        LocalDate deadline = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Subtask card = new Subtask(name, description, estimatedHours, deadline);
        cards.add(card);
    }

    public void viewTask(){
        System.out.println("1. Add Subtask\n2. View Subtask\n3. View Report\n4. View Workload\n5. Exit");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                addSubtask();
                viewTask();
                break;
            case 2:
                viewSubtask();
                viewTask();
                break;
            case 3:
                viewReport();
                viewTask();
                break;
            case 4:
                viewWorkload();
                viewTask();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                viewTask();
        }
    }

    public void addWorkload(User user, ArrayList<Subtask> cards) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which Subtask do you want to assign to " + user.getUsername() + "?");
        int choice = 1;
        ArrayList<Subtask> card = new ArrayList<>();
        while (true) {
            for (int i = 0; i < this.cards.size(); i++) {
                System.out.println(i + 1 + ". " + this.cards.get(i).getTitle());
            }
            System.out.print("Enter the number of the subtask (0 for exit): ");
            choice = input.nextInt();
            if(choice == 0) break;
            card.add(this.cards.get(choice - 1));
        }
        System.out.println("ks");
        Workload workload = new Workload(user, card);
        workloads.add(workload);
    }

    public void addReport(ProgressReport report) {
        reports.add(report);
    }

    public void assignMember(User user) {
        assignedMembers.add(user);
    }

    public void viewSubtask() {
        System.out.println("Subtasks: ");
        for (int i = 0; i < cards.size(); i++) {
            System.out.println((i + 1) + ". " + cards.get(i).getTitle());
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Which subtask do you want to view: ");
        int choice = input.nextInt();
        cards.get(choice - 1).viewSubtask();
    }

    public void viewReport(){
        System.out.println("Reports reported by: ");
        for(int i = 0; i < reports.size(); i++){
            System.out.println((i + 1) + ". " + reports.get(i).getReportedBy().getUsername());
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Who's report do you want to view: ");
        int choice = input.nextInt();
        reports.get(choice - 1).viewReport();
    }

    public void viewWorkload(){
        System.out.println("Workloads: ");
        for (int i = 0; i < workloads.size(); i++) {
            System.out.println((i + 1) + ". " + workloads.get(i).getAssignedTo().getUsername());
        }
        Scanner input = new Scanner(System.in);
        System.out.print("Who's workload do you want to view: ");
        int choice = input.nextInt();
        workloads.get(choice - 1).viewWorkload();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean getCompleted() {
        return completed;
    }

    public ArrayList<User> getAssignedMembers() {
        return assignedMembers;
    }

    public ArrayList<Subtask> getCards() {
        return cards;
    }

    public ArrayList<ProgressReport> getReports() {
        return reports;
    }

    public ArrayList<Workload> getWorkloads() {
        return workloads;
    }

}
