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
        while(option != 6){
            System.out.println("1. Update Title\n2. Update Description\n3. Add Task\n4. Add Subtask\n5. Assign Workload\n6. Exit");
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
                    proj.addTask();
                    break;
                case 4:
                    System.out.println("Choose the task you want to add a subtask to: ");
                    for(int i = 0; i < proj.getTaskList().size(); i++){
                        System.out.println(i + 1 + ". " + proj.getTaskList().get(i).getName());
                    }
                    System.out.print("Enter your choice: ");
                    int taskChoice = input.nextInt();
                    proj.getTaskList().get(taskChoice - 1).addSubtask();
                    break;
                case 5:
                    System.out.println("Choose the task you want to assign workload to: ");
                    for(int i = 0; i < proj.getTaskList().size(); i++){
                        System.out.println(i + 1 + ". " + proj.getTaskList().get(i).getName());
                    }
                    System.out.print("Enter your choice: ");
                    int taskChoice2 = input.nextInt();
                    proj.getTaskList().get(taskChoice2 - 1).addWorkload(proj.getInvolvedMembers().get(0), proj.getTaskList().get(taskChoice2 - 1).getCards());
                    break;
                case 6:
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
        while(option != 3){
            System.out.println("1. Add Member\n2. Remove Member\n3.Exit");
            option = input.nextInt();
            switch(option){
                case 1:
                    proj.addMember(user, proj);
                    break;
                case 2:
                    proj.removeMember(user, proj);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
            }
        }
    }
}