package project;

import crud.User;
import java.util.ArrayList;
import java.util.UUID;

public class Workload {
    private UUID id;
    private int estHours;
    private ArrayList<Subtask> job;
    private User assignedTo;

    public Workload(User assignedTo, ArrayList<Subtask> job) {
        this.id = UUID.randomUUID();
        this.assignedTo = assignedTo;
        this.job = job;
        this.estHours = 0;
        calculateEstHours();
    }

    public void calculateEstHours() {
        for(Subtask subtask : job) {
            this.estHours += subtask.getEstHours();
        }
    }

    public void viewWorkload(){
        System.out.println("ID: " + this.id.toString()  + "\nEstimated Hours: " + this.estHours + "\nSubtasks Assigned: ");
        for(Subtask subtask : job) {
            System.out.println("1. " + subtask.getTitle());
        }
    }

    public int getEstHours() {
        return estHours;
    }

    public ArrayList<Subtask> getJob() {
        return job;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setEstHours(int estHours) {
        this.estHours = estHours;
    }

    public void setJob(ArrayList<Subtask> job) {
        this.job = job;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }
}
