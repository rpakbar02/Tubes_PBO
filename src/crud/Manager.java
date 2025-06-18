package crud;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import project.ProgressReport;
import project.Project;
public class Manager extends Role {
    public Manager() {
        id = UUID.randomUUID();
        role = "Manager";
    }

    public void approveReport(ProgressReport report) {
        report.setApprovalStat(true);
    }

    public void manageProject(ArrayList<Project> project){
        for(int i = 0; i < project.size(); i++){
            Project p = project.get(i);
            System.out.println(i + 1 + ". " + p.getName());
        }
        System.out.print("Choose which project you wish to manage: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        Project proj = project.get(choice - 1);
        int option = 0;
        while(option != 7){
            System.out.println("1. Update Title\n2. Update Description\n3. View Project\n4. Add Task\n5. Add Subtask\n6. Assign Workload\n7. Exit");
            option = input.nextInt();
            switch(option){
                case 1:
                    System.out.print("Enter new title: ");
                    String title = input.next();
                    proj.setName(title);
                    break;
                case 2:
                    System.out.print("Enter new description: ");
                    String description = input.next();
                    proj.setDescription(description);
                    break;
                case 3:
                    proj.showData();
                    System.out.println("1. Manage task\n2. Exit");
                    int pilihan = input.nextInt();
                    while(pilihan != 1 && pilihan != 2){
                        switch (pilihan) {
                            case 1:
                                System.out.println("Please choose a task to view: ");
                                for(int i = 0; i < proj.getTaskList().size(); i++) System.out.println((i + 1) + ". " + proj.getTaskList().get(i).getName());
                                int taskChoice = input.nextInt();
                                proj.getTaskList().get(pilihan - 1).viewTask();
                                break;
                            case 2:
                                System.out.println("Exiting...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 4:
                    proj.addTask();
                    System.out.println("Task added successfully!");
                    break;
                case 5:
                    System.out.println("Choose the task you want to add a subtask to: ");
                    for(int i = 0; i < proj.getTaskList().size(); i++){
                        System.out.println(i + 1 + ". " + proj.getTaskList().get(i).getName());
                    }
                    System.out.print("Enter your choice: ");
                    int taskChoice = input.nextInt();
                    proj.getTaskList().get(taskChoice - 1).addSubtask();
                    System.out.println("Subtask added successfully!");
                    break;
                case 6:
                    System.out.println("Choose the task you want to assign workload to: ");
                    for(int i = 0; i < proj.getTaskList().size(); i++){
                        System.out.println(i + 1 + ". " + proj.getTaskList().get(i).getName());
                    }
                    System.out.print("Enter your choice: ");
                    int taskChoice2 = input.nextInt();
                    System.out.println("Choose the member you want to assign workload to: ");
                    for(int i = 0; i < proj.getInvolvedMembers().size(); i++){
                        System.out.println(i + 1 + ". " + proj.getInvolvedMembers().get(i).getUsername());
                    }
                    System.out.print("Enter your choice: ");
                    int memberChoice = input.nextInt();
                    proj.getTaskList().get(taskChoice2 - 1).addWorkload(proj.getInvolvedMembers().get(memberChoice - 1), proj.getTaskList().get(taskChoice2 - 1).getCards());
                    System.out.println("Workload assigned successfully!");
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
            }
        }
    }

    public void manageProject(ArrayList<Project> project, User user){
        for(int i = 0; i < project.size(); i++){
            Project p = project.get(i);
            System.out.println(i + 1 + ". " + p.getName());
        }
        System.out.print("Choose which project you wish to manage: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        Project proj = project.get(choice - 1);
        int option = 0;
        while(option != 1 && option != 2){
            System.out.println("1. Add Member\n2. Remove Member");
            option = input.nextInt();
            switch(option){
                case 1:
                    proj.addMember(user, proj);
                    System.out.println("Member added successfully!");
                    break;
                case 2:
                    boolean found = false;
                    for(User member : proj.getInvolvedMembers()){
                        if(member.getUsername().equals(user.getUsername())){
                            found = true;
                        }
                    }
                    if(!found){
                        System.out.println("Member not found. Please try again.");
                        break;
                    }
                    proj.removeMember(user, proj);
                    System.out.println("Member removed successfully!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}