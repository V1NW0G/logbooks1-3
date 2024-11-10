package com.example.myjoblist;

public class Job {

    int id;
    String title;
    String description;
    boolean isDone; // New field to track if the job is completed

    // Default constructor
    public Job() {
        this.isDone = false; // Default to not done
    }

    // Constructor with id, title, description, and isDone status
    public Job(int id, String title, String description, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    // Constructor without id, defaulting isDone to false
    public Job(String title, String description) {
        this.title = title;
        this.description = description;
        this.isDone = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Get title and add "(Done)" if the job is completed
    public String getTitle() {
        return isDone ? title + " (Done)" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter for isDone field
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}