package project;

import crud.User;
import java.time.LocalDate;
import java.util.UUID;

public class ProgressReport {
    private UUID id;
    private String content;
    private LocalDate date;
    private int hoursSpent;
    private User reportedBy;
    private boolean approvalStat;

    public ProgressReport(String content, LocalDate date, int hoursSpent, User reportedBy) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.date = date;
        this.hoursSpent = hoursSpent;
        this.reportedBy = reportedBy;
        this.approvalStat = false;
    }

    public void submitReport(ProgressReport report, Task task){
        task.addReport(report);
    }

    public int calculateOverload(Task task){
        int overload = 0;
        for(Workload workload : task.getWorkloads()){
            if(workload.getAssignedTo() == this.reportedBy){
                if(workload.getEstHours() < this.hoursSpent){
                    overload = this.hoursSpent - workload.getEstHours();
                }
            }
        }
        return overload;
    }

    public void viewReport(){
        System.out.println("Report ID: " + id);
        System.out.println("Report Content: " + content);
        System.out.println("Report Date: " + date);
        System.out.println("Hours Spent: " + hoursSpent);
        if(approvalStat){
            System.out.println("Approved");
        }else{
            System.out.println("Not Approved");
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHoursSpent() {
        return hoursSpent;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public boolean isApprovalStat() {
        return approvalStat;
    }

    public void setApprovalStat(boolean approvalStat) {
        this.approvalStat = approvalStat;
    }

    public void setHoursSpent(int hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }
}
