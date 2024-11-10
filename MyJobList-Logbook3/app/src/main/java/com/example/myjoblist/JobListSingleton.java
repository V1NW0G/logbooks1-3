package com.example.myjoblist;

import android.content.Context;
import java.util.ArrayList;

public class JobListSingleton {

    // Single instance of JobListSingleton
    private static JobListSingleton INSTANCE;
    // List to store job objects
    private ArrayList<Job> jobList;
    // Database helper to manage job data in SQLite
    private DBHelper dbHelper;

    // Private constructor to prevent direct instantiation and initialize the job list from the database
    private JobListSingleton(Context context) {
        dbHelper = new DBHelper(context); // Updated to pass only Context
        jobList = dbHelper.listJobs(); // Load jobs from the database
    }

    // Method to get the single instance of JobListSingleton
    public static JobListSingleton getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new JobListSingleton(context);
        }
        return INSTANCE;
    }

    // Method to get the list of jobs
    public ArrayList<Job> getJobList() {
        return jobList;
    }

    // Method to set the job list
    public void setJobList(ArrayList<Job> jobList) {
        this.jobList = jobList;
    }

    // Method to add a new job to the list and database
    public void addJob(Job job) {
        dbHelper.insertJob(job); // Insert job into the database
        jobList.add(job); // Add job to the list
    }

    // Method to delete a job by position
    public void deleteJob(int position) {
        int jobId = jobList.get(position).getId();
        dbHelper.deleteJob(jobId); // Delete job from the database by its ID
        jobList.remove(position); // Remove job from the list
    }

    // Method to update a job's title and description in the list and database
    public void updateJob(int position, Job updatedJob) {
        Job currentJob = jobList.get(position);
        currentJob.setTitle(updatedJob.getTitle());
        currentJob.setDescription(updatedJob.getDescription());
        dbHelper.updateJob(currentJob.getId(), updatedJob); // Update job in the database
    }

    // Method to mark a job as done, update it in the database, and move it to the bottom of the list
    public void markJobAsDone(int position) {
        Job job = jobList.get(position);
        job.setCompleted(true); // Mark the job as completed
        job.setTitle(job.getTitle() + " (Done)"); // Append "(Done)" to the job title

        dbHelper.updateJob(job.getId(), job); // Update the job in the database

        // Remove the job from its current position and add it to the end of the list
        jobList.remove(position);
        jobList.add(job);
    }
}