package project;

import java.time.LocalDate;
import java.util.UUID;

public class Subtask {
    private UUID id;
    private String title;
    private String jobDesc;
    private int estHours;
    private LocalDate dueDate;
    private boolean completed;

    public Subtask(String title, String jobDesc, int estHours, LocalDate dueDate){
        this.id = UUID.randomUUID();
        this.title = title;
        this.jobDesc = jobDesc;
        this.estHours = estHours;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public void toggleCompleted(){
        if(this.completed){
            this.completed = false;
        } else {
            this.completed = true;
        }
    }

    public void viewSubtask(){
        System.out.println("Title: " + this.title + "\nJob Description: " + this.jobDesc + "\nEstimated Hours: " + this.estHours + "\nDue Date: " + this.dueDate);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setJobDesc(String jobDesc){
        this.jobDesc = jobDesc;
    }

    public void setEstHours(int estHours){
        this.estHours = estHours;
    }

    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public String getTitle(){
        return title;
    }

    public String getJobDesc(){
        return jobDesc;
    }

    public int getEstHours(){
        return estHours;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public boolean getCompleted(){
        return completed;
    }
}
