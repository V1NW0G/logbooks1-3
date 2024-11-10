package com.example.myjoblist;

public class Job {

    private int id; // Unique identifier for the job
    private String title; // Title of the job
    private String description; // Description of the job
    private boolean completed; // Completion status of the job (true if completed, false otherwise)

    // Default constructor
    public Job() {
    }

    // Constructor to initialize all fields
    public Job(int id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Constructor without id, used when creating a new job
    public Job(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for completed status (returns 1 if completed, 0 if not, for database compatibility)
    public int getCompleted() {
        return completed ? 1 : 0;
    }

    // Setter for completed status using an integer (1 for completed, 0 for not completed)
    public void setCompleted(int completed) {
        this.completed = (completed == 1);
    }

    // Setter for completed status using boolean
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Method to check if the job is completed
    public boolean isCompleted() {
        return completed;
    }
}