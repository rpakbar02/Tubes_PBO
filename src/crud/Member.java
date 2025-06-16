package crud;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;
import project.ProgressReport;
import project.Project;
import project.Task;

public class Member extends Role {
    public Member() {
        id = UUID.randomUUID();
        role = "Member";
    }
    public void writeReport(Project project, Task task, User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("Write your report: ");
        String content = input.nextLine();
        System.out.println("Hours worked: ");
        int hours = input.nextInt();
        ProgressReport report = new ProgressReport(content, LocalDate.now(), hours, user);
        report.submitReport(report, task);
    }
}
